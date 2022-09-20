package com.harxsh.beanvalidation.controller;

import com.harxsh.beanvalidation.dto.UserRequest;
import com.harxsh.beanvalidation.entity.User;
import com.harxsh.beanvalidation.exception.UserNotFoundException;
import com.harxsh.beanvalidation.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<User> addUser(@RequestBody @Valid UserRequest request) {
        return new ResponseEntity<>(userService.addUser(request), HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.finaAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) throws UserNotFoundException {
        return ResponseEntity.ok(userService.findByUserId(id));
    }
}
