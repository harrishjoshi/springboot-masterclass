package com.harxsh.spring.data.jpa.repository;

import com.harxsh.spring.data.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstName(String firstName);

    List<User> findByFirstNameContaining(String name);

    List<User> findByLastNameNotNull();

    List<User> findByAddressTemp(String temp);
}
