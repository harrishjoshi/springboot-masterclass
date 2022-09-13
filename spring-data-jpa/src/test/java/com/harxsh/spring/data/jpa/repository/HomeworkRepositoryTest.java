package com.harxsh.spring.data.jpa.repository;

import com.harxsh.spring.data.jpa.entity.Homework;
import com.harxsh.spring.data.jpa.entity.Tutor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

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

    @Test
    void findAllPagination() {
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0, 3);

        Pageable secondPageWithTwoRecords =
                PageRequest.of(1, 2);

        List<Homework> homeworks =
                homeworkRepository.findAll(secondPageWithTwoRecords).getContent();

        long totalElements =
                homeworkRepository.findAll(secondPageWithTwoRecords)
                        .getTotalElements();
        long totalPages =
                homeworkRepository.findAll(secondPageWithTwoRecords)
                        .getTotalPages();

        log.info("Homeworks: {}", homeworks);
        log.info("Total Elements: {}", totalElements);
        log.info("Total Pages: {}", totalPages);
    }

    @Test
    void printAllSorting() {
        Pageable sortByTitleDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("title").descending()
                );

        Pageable sortByCreditHoursDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("creditHours").descending()
                );

        Pageable sortByTitleAndCreditHoursDesc =
                PageRequest.of(
                        0,
                        2,
                        Sort.by("creditHours").descending()
                                .and(Sort.by("creditHours").descending())
                );

        List<Homework> homeworks =
                homeworkRepository.findAll(sortByCreditHoursDesc).getContent();
        log.info("Homeworks: {}", homeworks);
    }

    @Test
    void printTitleContaining() {
        Pageable firstPageTenRecords =
                PageRequest.of(0, 10);

        List<Homework> homeworks =
                homeworkRepository.findByTitleContaining(
                        "S",
                        firstPageTenRecords
                ).getContent();
        log.info("Homeworks: {}", homeworks);
    }
}