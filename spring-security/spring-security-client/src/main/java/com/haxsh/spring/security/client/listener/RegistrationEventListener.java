package com.haxsh.spring.security.client.listener;

import com.haxsh.spring.security.client.entity.User;
import com.haxsh.spring.security.client.event.RegistrationEvent;
import com.haxsh.spring.security.client.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class RegistrationEventListener implements ApplicationListener<RegistrationEvent> {

    private final UserService userService;

    @Override
    public void onApplicationEvent(RegistrationEvent event) {
        // create the verification token for user with link
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        userService.saveVerificationToken(user, token);

        // send mail to user
        String url = event.getApplicationUrl() + "/verify-registration?token=" + token;

        // sendVerificationEmail()

        log.info("Click the link to verify your account: {} ", url);
    }
}
