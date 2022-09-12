package com.harxsh.spring.data.jpa.repository;

import com.harxsh.spring.data.jpa.entity.Homework;
import com.harxsh.spring.data.jpa.entity.Tutor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class TutorRepositoryTest {

    @Autowired
    private TutorRepository tutorRepository;

    @Test
    void saveTutor() {
        Homework dsaHomework = Homework.builder()
                .title("DSA")
                .creditHours(10)
                .build();

        Homework jsHomework = Homework.builder()
                .title("JavaScript")
                .creditHours(10)
                .build();

        Tutor tutor = Tutor.builder()
                .firstName("Harish")
                .lastName("Joshi")
                .homeworks(List.of(dsaHomework, jsHomework))
                .build();

        // save tutor
        tutorRepository.save(tutor);
    }

    @Test
    void printAllTutor() {
        List<Tutor> tutors = tutorRepository.findAll();
        log.info("Tutors: {}", tutors);
    }
}