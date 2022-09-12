package com.harxsh.spring.data.jpa.repository;

import com.harxsh.spring.data.jpa.entity.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework, Long> {
}
