package com.harxsh.spring.data.jpa.repository;

import com.harxsh.spring.data.jpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstName(String firstName);

    List<User> findByFirstNameContaining(String name);

    List<User> findByLastNameNotNull();

    List<User> findByAddressTemp(String temp);

    @Query("select u.firstName from User u where u.email = ?1")
    String findUserFirstNameByEmail(String email);

    @Modifying
    @Transactional
    @Query(
            value = "update tbl_user set first_name = ?1 where email = ?2",
            nativeQuery = true
    )
    int updateUserNameByEmail(String firstName, String email);
}
