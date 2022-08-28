package com.harxsh.spring.rest.api.controller.v2;

import com.harxsh.spring.rest.api.entity.Student;
import com.harxsh.spring.rest.api.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController("v2.StudentController")
@RequestMapping("/api/v2/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping("add")
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
        try {
            studentService.addStudent(student);
            return new ResponseEntity<>(student, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> fetchStudents() {
        try {
            List<Student> students = studentService.fetchStudents();
            return new ResponseEntity<>(
                    students,
                    students.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable Long id) {
        try {
            Student student = studentService.findStudentById(id);
            return new ResponseEntity<>(
                    student,
                    student == null ? HttpStatus.NOT_FOUND : HttpStatus.OK
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        try {
            studentService.deleteStudentById(id);
            return new ResponseEntity<>("Student deleted successfully.", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{id}/update")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @Valid @RequestBody Student student) {
        try {
            Student updatedStudent = studentService.updateStudentById(id, student);
            return new ResponseEntity<>(
                    updatedStudent,
                    updatedStudent == null ? HttpStatus.NOT_FOUND : HttpStatus.OK
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("find-by-name")
    public List<Student> fetchByName(@RequestParam String name) {
        return studentService.findByName(name);
    }
}
