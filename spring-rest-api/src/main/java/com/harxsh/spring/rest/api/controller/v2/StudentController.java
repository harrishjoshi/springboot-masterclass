package com.harxsh.spring.rest.api.controller.v2;

import com.harxsh.spring.rest.api.entity.Student;
import com.harxsh.spring.rest.api.exception.StudentNotFoundException;
import com.harxsh.spring.rest.api.service.StudentService;
import com.harxsh.spring.rest.api.util.StudentUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("add")
    public ResponseEntity<Student> addStudent(@Valid @RequestBody Student student) {
        try {
            logger.info("Inside student add.");
            return new ResponseEntity<>(studentService.addStudent(student), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> fetchStudents() {
        try {
            logger.info("Inside student fetch.");
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
    public Student fetchStudentById(@PathVariable Long id) throws StudentNotFoundException {
        return studentService.fetchStudentById(id);
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
    public ResponseEntity<List<Student>> fetchStudentsByName(@RequestParam String name) {
        try {
            List<Student> studentsByName = studentService.fetchAllByName(name);
            return new ResponseEntity<>(
                    studentsByName,
                    StudentUtil.isNotNullAndEmpty(studentsByName) ? HttpStatus.OK : HttpStatus.NOT_FOUND
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("find-first-by-name")
    public ResponseEntity<Student> fetchFirstByName(@RequestParam String name) {
        try {
            Student studentsByName = studentService.fetchFirstByName(name);
            return new ResponseEntity<>(
                    studentsByName, studentsByName == null ? HttpStatus.NOT_FOUND : HttpStatus.OK
            );
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
