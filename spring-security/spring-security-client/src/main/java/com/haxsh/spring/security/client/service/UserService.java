package com.haxsh.spring.security.client.service;

import com.haxsh.spring.security.client.entity.User;
import com.haxsh.spring.security.client.entity.UserModel;

public interface UserService {

    User registerUser(UserModel userModel);

    void saveVerificationToken(User us, String token);

    String validateVerificationToken(String token);
}
