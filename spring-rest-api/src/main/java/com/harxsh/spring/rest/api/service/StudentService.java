package com.harxsh.spring.rest.api.service;

import com.harxsh.spring.rest.api.entity.Student;
import com.harxsh.spring.rest.api.exception.StudentNotFoundException;

import java.util.List;

public interface StudentService {
    void addStudent(Student student);

    List<Student> fetchStudents();

    Student findStudentById(Long id) throws StudentNotFoundException;

    void deleteStudentById(Long id);

    Student updateStudentById(Long id, Student student);

    List<Student> findByName(String name);
}
