package com.harxsh.spring.data.jpa.repository;

import com.harxsh.spring.data.jpa.entity.Address;
import com.harxsh.spring.data.jpa.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void addUser() {
        User user = User.builder()
                .firstName("Harish")
                .lastName("Joshi")
                .email("harishjoshi@gmail.com")
                //.permAddress("ddl")
                //.tempAddress("ktm")
                .build();

        // save user object
        userRepository.save(user);
    }

    @Test
    void addStudentWithAddress() {
        Address address = Address.builder()
                .perm("ddl")
                .temp("ktm")
                .build();

        User user = User.builder()
                .firstName("Joshi")
                .lastName("Harish")
                .email("joshiharish@gmail.com")
                .address(address)
                .build();

        // save user object
        userRepository.save(user);
    }

    @Test
    void printAllUsers() {
        List<User> users = userRepository.findAll();
        log.info("Users: {}", users);
    }

    @Test
    void printUsersByFirstName() {
        List<User> users = userRepository.findByFirstName("Joshi");
        log.info("Users: {}", users);
    }

    @Test
    void printUsersByFirstNameContaining() {
        List<User> users = userRepository.findByFirstNameContaining("sh");
        log.info("Users: {}", users);
    }

    @Test
    void printUsersByLastNameNotNull() {
        List<User> users = userRepository.findByLastNameNotNull();
        log.info("Users: {}", users);
    }

    @Test
    void printUsersAddressTempAddress() {
        List<User> users = userRepository.findByAddressTemp("ktm");
        log.info("Users: {}", users);
    }

    @Test
    void printUserFirstNameByEmail() {
        String firstName = userRepository.findUserFirstNameByEmail("joshiharish@gmail.com");
        log.info("User First Name: {}", firstName);
    }
}