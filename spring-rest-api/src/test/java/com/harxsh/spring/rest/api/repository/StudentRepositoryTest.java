package com.harxsh.spring.rest.api.repository;

import com.harxsh.spring.rest.api.entity.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestEntityManager entityManager;

    @BeforeEach
    void setUp() {
        Student student = Student.builder()
                .name("Harish Joshi")
                .age(25)
                .courses("Java, Javascript")
                .build();

        entityManager.persist(student);
    }

    @Test
    void whenFindById_thenReturnStudent() {
        Student student = studentRepository.findById(1L).get();
        assertEquals(student.getName(), "Harish Joshi");
    }
}