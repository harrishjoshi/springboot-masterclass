package com.harxsh.spring.rest.api.service;

import com.harxsh.spring.rest.api.entity.Student;
import com.harxsh.spring.rest.api.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        Student student = Student.builder()
                .name("Harish")
                .age(25)
                .courses("Java, Spring Boot, Microservice")
                .build();

        Mockito.when(studentRepository.findFirstByName("Harish"))
                .thenReturn(student);
    }

    @Test
    @DisplayName("Get data based on valid Student name")
    void whenValidStudentName_thenStudentShouldBeFound() {
        String studentName = "Harish";
        Student studentByName = studentService.fetchFirstByName(studentName);
        assertEquals(studentByName.getName(), studentName);
    }
}