package com.harxsh.spring.rest.api.controller.v2;

import com.harxsh.spring.rest.api.entity.Student;
import com.harxsh.spring.rest.api.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    private Student student;

    @BeforeEach
    void setUp() {
        student = Student.builder()
                .name("Harish Joshi")
                .age(25)
                .courses("Java, Microservice")
                .build();
    }

    @Test
    void addStudent() throws Exception {
        Student inputStudent = Student.builder()
                .name("Harish Joshi")
                .age(25)
                .courses("Java, Spring, Microservices")
                .build();

        Mockito.when(studentService.addStudent(inputStudent))
                .thenReturn(student);

        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    {
                                        "name": "Harish Joshi",
                                        "age": 25,
                                        "courses": "Java, Spring, Microservices"
                                    }
                                }
                                """))
                .andExpect(result -> new ResponseEntity<>(HttpStatus.OK));
    }

    @Test
    void fetchStudentById() throws Exception {
        Mockito.when(studentService.fetchStudentById(1L))
                .thenReturn(student);
        mockMvc.perform(post("/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    {
                                        "name": "Harish Joshi",
                                        "age": 25,
                                        "courses": "Java, Spring, Microservices"
                                    }
                                }
                                """))
                .andExpect(result -> jsonPath("$.courses").value(student.getCourses()))
                .andExpect(result -> new ResponseEntity<>(HttpStatus.OK));

    }
}