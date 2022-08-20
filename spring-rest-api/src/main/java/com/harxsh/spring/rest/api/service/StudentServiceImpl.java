package com.harxsh.spring.rest.api.service;

import com.harxsh.spring.rest.api.entity.Student;
import com.harxsh.spring.rest.api.repository.StudentRepository;
import com.harxsh.spring.rest.api.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public List<Student> fetchStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudentById(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setName(StringUtil.isNotEmpty(student.getName()) ? student.getName() : existingStudent.getName());
            existingStudent.setAge(student.getAge() != 0 ? student.getAge() : existingStudent.getAge());
            existingStudent.setCourses(StringUtil.isNotEmpty(student.getCourses()) ? student.getCourses() : existingStudent.getCourses());
            studentRepository.save(existingStudent);
        }

        return existingStudent;
    }
}
