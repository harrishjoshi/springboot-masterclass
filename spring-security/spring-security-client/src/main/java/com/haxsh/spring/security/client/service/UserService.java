package com.haxsh.spring.security.client.service;

import com.haxsh.spring.security.client.entity.User;
import com.haxsh.spring.security.client.entity.UserModel;
import com.haxsh.spring.security.client.entity.VerificationToken;

import java.util.Optional;

public interface UserService {

    User registerUser(UserModel userModel);

    void saveVerificationToken(User us, String token);

    String validateVerificationToken(String token);

    VerificationToken generateVerificationToken(String oldToken);

    User findUserByEmail(String email);

    void createPasswordResetTokenForUser(User user, String token);

    String validatePasswordResetToken(String token);

    Optional<User> getUserByPasswordResetToken(String token);

    void changePassword(User user, String newPassword);

    boolean validateOldPassword(User user, String oldPassword);
}