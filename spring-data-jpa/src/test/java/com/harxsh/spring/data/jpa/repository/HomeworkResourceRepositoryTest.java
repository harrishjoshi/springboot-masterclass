package com.harxsh.spring.data.jpa.repository;

import com.harxsh.spring.data.jpa.entity.Homework;
import com.harxsh.spring.data.jpa.entity.HomeworkResource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class HomeworkResourceRepositoryTest {

    @Autowired
    private HomeworkResourceRepository homeworkResourceRepository;

    @Test
    void saveHomeworkResource() {
        Homework homework = Homework.builder()
                .title("Spring Boot")
                .creditHours(10)
                .build();

        HomeworkResource homeworkResource = HomeworkResource.builder()
                .content("Spring boot master class")
                .externalUrl("spring.io")
                .homework(homework)
                .build();

        // save homework resource
        homeworkResourceRepository.save(homeworkResource);
    }

    @Test
    void printAllHomeworkResources() {
        List<HomeworkResource> homeworkResources =
                homeworkResourceRepository.findAll();
        log.info("HomeworkResources: {}", homeworkResources);
    }
}