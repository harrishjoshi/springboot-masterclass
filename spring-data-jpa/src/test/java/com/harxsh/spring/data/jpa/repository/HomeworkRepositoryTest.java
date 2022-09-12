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
class HomeworkRepositoryTest {

    @Autowired
    private HomeworkRepository homeworkRepository;

    @Test
    void printAllHomework() {
        List<Homework> homeworks = homeworkRepository.findAll();
        log.info("Homeworks: {}", homeworks);
    }

    @Test
    void saveHomeworkWithTutor() {
        Tutor tutor = Tutor.builder()
                .firstName("Harish")
                .lastName("Joshi")
                .build();

        Homework homework = Homework.builder()
                .title("Spring MVC")
                .creditHours(5)
                .tutor(tutor)
                .build();

        // save homework
        homeworkRepository.save(homework);
    }
}