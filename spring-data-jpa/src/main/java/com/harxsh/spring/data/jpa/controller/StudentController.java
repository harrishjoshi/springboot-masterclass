package com.harxsh.spring.data.jpa.controller;

import com.harxsh.spring.data.jpa.entity.Guardian;
import com.harxsh.spring.data.jpa.entity.Response;
import com.harxsh.spring.data.jpa.entity.Student;
import com.harxsh.spring.data.jpa.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
@Slf4j
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping(value = "add", consumes = "application/json")
    public ResponseEntity<Response> addStudent(@RequestBody String payload) {
        String message;
        HttpStatus status;
        Student student = null;

        try {
            // get json object from payload
            JSONObject jsonObject = new JSONObject(payload);

            // student related data
            student = new Student();
            student.setEmail(jsonObject.getString("email"));
            student.setFirstName(jsonObject.getString("firstName"));
            student.setLastName(jsonObject.getString("lastName"));

            // student's guardian related data
            List<Guardian> guardians = new ArrayList<>();
            for (Object obj : jsonObject.getJSONArray("guardians")) {
                JSONObject object = new JSONObject(obj.toString());
                Guardian guardian = new Guardian();
                guardian.setEmail(object.getString("email"));
                guardian.setFirstName(object.getString("firstName"));
                guardian.setLastName(object.getString("lastName"));
                guardian.setPhone(object.getString("phone"));
                guardian.setStudent(student);
                guardians.add(guardian);
            }

            student.setGuardians(guardians);
            student = studentService.addStudent(student);
            message = "Student save successfully.";
            status = HttpStatus.OK;
        } catch (Exception e) {
            student = null;
            e.printStackTrace();
            message = "Failed to save student. Please try again.";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(new Response(message, student), status);
    }

    @GetMapping("")
    public ResponseEntity<Response> findAll() {
        String message;
        HttpStatus status;
        List<Student> students = null;

        try {
            students = studentService.findAll();
            if (!students.isEmpty()) {
                status = HttpStatus.OK;
                message = "Students fetched successfully.";
            } else {
                status = HttpStatus.NOT_FOUND;
                message = "Students data not found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Failed to fetch students data.";
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(new Response(message, students), status);
    }


    @GetMapping("{id}")
    public ResponseEntity<Response> findById(@PathVariable("id") Long studentId) {
        String message;
        HttpStatus status;
        Student student = null;

        try {
            student = studentService.findById(studentId);
            if (student != null) {
                status = HttpStatus.OK;
                message = "Student data with id: " + studentId + " fetched successfully.";
            } else {
                status = HttpStatus.NOT_FOUND;
                message = "Student data with id: " + studentId + " not found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Failed to fetch student data with id: " + studentId;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(new Response(message, student), status);
    }
}
