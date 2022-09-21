package com.haxsh.spring.security.client.controller;

import com.haxsh.spring.security.client.entity.PasswordModel;
import com.haxsh.spring.security.client.entity.User;
import com.haxsh.spring.security.client.entity.UserModel;
import com.haxsh.spring.security.client.entity.VerificationToken;
import com.haxsh.spring.security.client.event.RegistrationEvent;
import com.haxsh.spring.security.client.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;

    @PostMapping("user/registration")
    public String registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
        // save user
        User user = userService.registerUser(userModel);

        // publish registration event
        publisher.publishEvent(new RegistrationEvent(user, applicationUrl(request)));

        return "Success";
    }

    @GetMapping("verify-registration")
    public String verifyRegistration(@RequestParam String token) {
        return userService.validateVerificationToken(token);
    }

    @GetMapping("resent-verification-token")
    public String resendVerificationToken(
            @RequestParam("token") String oldToken,
            HttpServletRequest request
    ) {
        VerificationToken verificationToken = userService.generateVerificationToken(oldToken);
        resendVerificationTokenMail(verificationToken, applicationUrl(request));
        return "verification link sent";
    }

    @PostMapping("reset-password")
    public String resetPassword(
            @RequestBody PasswordModel passwordModel,
            HttpServletRequest request
    ) {
        User user = userService.findUserByEmail(passwordModel.getEmail());
        String url = "";
        if (user != null) {
            String token = UUID.randomUUID().toString();
            userService.createPasswordResetTokenForUser(user, token);
            url = passwordResetTokenMail(token, applicationUrl(request));
        }

        return url;
    }

    @PostMapping("save-password")
    public String savePassword(
            @RequestParam String token,
            @RequestBody PasswordModel passwordModel
    ) {
        String result = userService.validatePasswordResetToken(token);

        if (!result.equals("valid"))
            return "invalid token";

        Optional<User> user = userService.getUserByPasswordResetToken(token);
        if (user.isPresent()) {
            userService.changePassword(user.get(), passwordModel.getNewPassword());
            return "password reset successfully";
        } else return "invalid token";
    }

    @PostMapping("change-password")
    public String changePassword(
            @RequestBody PasswordModel passwordModel
    ) {
        User user = userService.findUserByEmail(passwordModel.getEmail());
        if (!userService.validateOldPassword(user, passwordModel.getOldPassword()))
            return "invalid old password";

        // update new password
        userService.changePassword(user, passwordModel.getNewPassword());

        return "password changed successfully";
    }

    private String passwordResetTokenMail(String token, String applicationUrl) {
        // send mail to user
        String url = applicationUrl + "/save-password?token=" + token;

        // sendVerificationEmail()
        log.info("Click the link to reset your password: {} ", url);

        return url;
    }

    private void resendVerificationTokenMail(VerificationToken verificationToken, String applicationUrl) {
        // send mail to user
        String url = applicationUrl + "/verify-registration?token=" + verificationToken.getToken();

        // sendVerificationEmail()
        log.info("Click the link to verify your account: {} ", url);
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}