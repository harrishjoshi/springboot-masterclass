package com.harxsh.spring.rest.api.repository;

import com.harxsh.spring.rest.api.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    List<Student> findByNameIgnoreCase(String name);
}
