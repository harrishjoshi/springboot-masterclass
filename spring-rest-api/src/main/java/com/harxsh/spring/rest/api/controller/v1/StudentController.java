package com.harxsh.spring.rest.api.controller.v1;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("v1.StudentController")
@RequestMapping("/api/v1/students")
public class StudentController {

    @GetMapping("")
    public ResponseEntity<List<Student>> getStudents() {
        return new ResponseEntity<>(
                List.of(new Student("Harxsh", 25, List.of("Java", "Spring", "Microservices"))),
                HttpStatus.OK
        );
    }

    record Student(String name, int age, List<String> courses) {}
}
