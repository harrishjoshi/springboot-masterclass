package com.harxsh.spring.rest.api.service;

import com.harxsh.spring.rest.api.entity.Student;
import com.harxsh.spring.rest.api.exception.StudentNotFoundException;

import java.util.List;

public interface StudentService {
    Student addStudent(Student student);

    List<Student> fetchStudents();

    Student fetchStudentById(Long id) throws StudentNotFoundException;

    void deleteStudentById(Long id);

    Student updateStudentById(Long id, Student student);

    List<Student> fetchAllByName(String name);

    Student fetchFirstByName(String name);
}
