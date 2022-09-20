package com.haxsh.spring.security.client.service;

import com.haxsh.spring.security.client.entity.User;
import com.haxsh.spring.security.client.entity.UserModel;
import com.haxsh.spring.security.client.entity.VerificationToken;
import com.haxsh.spring.security.client.repository.UserRepository;
import com.haxsh.spring.security.client.repository.VerificationTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final VerificationTokenRepository verificationTokenRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User registerUser(UserModel userModel) {
        User user = User.builder()
                .email(userModel.getEmail())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .role("USER")
                .password(passwordEncoder.encode(userModel.getPassword()))
                .build();

        // save user
        userRepository.save(user);

        return user;
    }

    @Override
    public void saveVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(user, token);
        // save verification token
        verificationTokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        VerificationToken verificationToken =
                verificationTokenRepository.findByToken(token);

        // return invalid when token is not available
        if (verificationToken == null)
            return "invalid token";

        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();

        // when token is expired, then delete token and return expired
        if (verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            verificationTokenRepository.delete(verificationToken);
            return "token expired";
        }

        // enable user and update
        user.setEnabled(true);
        userRepository.save(user);

        return "user verified successfully";
    }
}
