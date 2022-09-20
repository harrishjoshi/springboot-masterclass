package com.harxsh.beanvalidation.service;

import com.harxsh.beanvalidation.dto.UserRequest;
import com.harxsh.beanvalidation.entity.User;
import com.harxsh.beanvalidation.exception.UserNotFoundException;
import com.harxsh.beanvalidation.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User addUser(UserRequest request) {
        User user = User.build(
                0L, request.getName(), request.getEmail(), request.getMobile(),
                request.getGender(), request.getAge(), request.getCountry()
        );

        return userRepository.save(user);
    }

    public List<User> finaAllUsers() {
        return userRepository.findAll();
    }

    public User findByUserId(Long id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) return user.get();
        else throw new UserNotFoundException("user not found with id: " + id);
    }
}
