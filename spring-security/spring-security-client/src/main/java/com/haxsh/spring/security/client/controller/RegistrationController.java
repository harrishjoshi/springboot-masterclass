package com.haxsh.spring.security.client.controller;

import com.haxsh.spring.security.client.entity.User;
import com.haxsh.spring.security.client.entity.UserModel;
import com.haxsh.spring.security.client.event.RegistrationEvent;
import com.haxsh.spring.security.client.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
@RequiredArgsConstructor
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

    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
