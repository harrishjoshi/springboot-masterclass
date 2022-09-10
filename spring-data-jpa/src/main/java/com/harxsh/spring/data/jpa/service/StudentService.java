package com.harxsh.spring.data.jpa.service;

import com.harxsh.spring.data.jpa.entity.Student;
import com.harxsh.spring.data.jpa.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Student findById(Long studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public void deleteById(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public Student findStudentByEmail(String email) {
        return studentRepository.findStudentByEmail(email);
    }

    public Student findStudentByGuardianEmail(String email) {
        return studentRepository.findStudentByGuardianEmail(email);
    }

    public Student findStudentByGuardianEmail(Long id) {
        return studentRepository.findStudentByGuardianId(id);
    }
}
