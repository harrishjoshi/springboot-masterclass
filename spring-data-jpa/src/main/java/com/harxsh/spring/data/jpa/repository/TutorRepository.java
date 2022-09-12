package com.harxsh.spring.data.jpa.repository;


import com.harxsh.spring.data.jpa.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor, Long> {
}
