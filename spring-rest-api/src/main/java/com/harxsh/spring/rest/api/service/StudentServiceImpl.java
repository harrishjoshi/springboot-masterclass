package com.harxsh.spring.rest.api.service;

import com.harxsh.spring.rest.api.entity.Student;
import com.harxsh.spring.rest.api.exception.StudentNotFoundException;
import com.harxsh.spring.rest.api.repository.StudentRepository;
import com.harxsh.spring.rest.api.util.StudentUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> fetchStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student fetchStudentById(Long id) throws StudentNotFoundException {
        Optional<Student> student = studentRepository.findById(id);

        if (student.isEmpty())
            throw new StudentNotFoundException("Student with id " + id + " not found.");

        return student.get();
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudentById(Long id, Student student) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (existingStudent != null) {
            existingStudent.setName(StudentUtil.isNotEmpty(student.getName()) ? student.getName() : existingStudent.getName());
            existingStudent.setAge(student.getAge() != 0 ? student.getAge() : existingStudent.getAge());
            existingStudent.setCourses(StudentUtil.isNotEmpty(student.getCourses()) ? student.getCourses() : existingStudent.getCourses());
            studentRepository.save(existingStudent);
        }

        return existingStudent;
    }

    @Override
    public List<Student> fetchAllByName(String name) {
        return studentRepository.findByNameIgnoreCase(name);
    }

    @Override
    public Student fetchFirstByName(String name) {
        return studentRepository.findFirstByName(name);
    }
}
