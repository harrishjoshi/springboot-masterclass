package com.harxsh.spring.data.jpa.controller;

import com.harxsh.spring.data.jpa.controller.util.StudentUtil;
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
        Student student;

        try {
            // get json object from payload
            JSONObject jsonObject = new JSONObject(payload);

            // set student related data
            student = Student.builder()
                    .email(jsonObject.getString("email"))
                    .firstName(jsonObject.getString("firstName"))
                    .lastName(jsonObject.getString("lastName"))
                    .build();

            // student's guardian related data
            List<Guardian> guardians = new ArrayList<>();
            for (Object obj : jsonObject.getJSONArray("guardians")) {
                JSONObject object = new JSONObject(obj.toString());

                // set guardian related data
                Guardian guardian = Guardian.builder()
                        .email(object.getString("email"))
                        .firstName(object.getString("firstName"))
                        .lastName(object.getString("lastName"))
                        .phone(object.getString("phone"))
                        .student(student)
                        .build();

                // add guardian data to list
                guardians.add(guardian);
            }

            student.setGuardians(guardians);
            student = studentService.addStudent(student);
            message = "Student saved successfully.";
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
                message = StudentUtil.getFetchMessage("id", studentId);
            } else {
                status = HttpStatus.NOT_FOUND;
                message = StudentUtil.dataNotFoundMessage("id", studentId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Failed to fetch student data with id: " + studentId;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(new Response(message, student), status);
    }

    @PutMapping(value = "update/{id}", consumes = "application/json")
    public ResponseEntity<Response> updateStudent(
            @PathVariable("id") Long studentId,
            @RequestBody String payload
    ) {
        String message;
        HttpStatus status;
        Student student = null;

        try {
            Student existingStudent = studentService.findById(studentId);
            if (existingStudent != null) {
                // get json object from payload
                JSONObject jsonObject = new JSONObject(payload);

                // set student related data
                student = Student.builder()
                        .id(existingStudent.getId())
                        .email(jsonObject.getString("email"))
                        .firstName(jsonObject.getString("firstName"))
                        .lastName(jsonObject.getString("lastName"))
                        .guardians(existingStudent.getGuardians())
                        .build();

                student = studentService.addStudent(student);
                message = "Student with id: " + studentId + " updated successfully.";
                status = HttpStatus.OK;
            } else {
                status = HttpStatus.NOT_FOUND;
                message = StudentUtil.dataNotFoundMessage("id", studentId);
            }
        } catch (Exception e) {
            student = null;
            e.printStackTrace();
            message = "Failed to update student with id: " + studentId;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(new Response(message, student), status);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Response> deleteById(@PathVariable("id") Long studentId) {
        String message;
        HttpStatus status;

        try {
            Student student = studentService.findById(studentId);
            if (student != null) {
                studentService.deleteById(studentId);
                status = HttpStatus.OK;
                message = "Student with id: " + studentId + " deleted successfully.";
            } else {
                status = HttpStatus.NOT_FOUND;
                message = StudentUtil.dataNotFoundMessage("id", studentId);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Failed to delete student with id: " + studentId;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(new Response(message, null), status);
    }

    @GetMapping("find-by-email")
    public ResponseEntity<Response> findByEmail(@RequestParam String email) {
        String message;
        HttpStatus status;
        Student student = null;

        try {
            student = studentService.findStudentByEmail(email);
            if (student != null) {
                status = HttpStatus.OK;
                message = StudentUtil.getFetchMessage("email", email);
            } else {
                status = HttpStatus.NOT_FOUND;
                message = StudentUtil.dataNotFoundMessage("email", email);
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Failed to fetch student data with email: " + email;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(new Response(message, student), status);
    }

    @GetMapping("find-by-guardian-email")
    public ResponseEntity<Response> findByGuardianEmail(@RequestParam String email) {
        String message;
        HttpStatus status;
        Student student = null;

        try {
            student = studentService.findStudentByGuardianEmail(email);
            if (student != null) {
                status = HttpStatus.OK;
                message = "Student data with guardian email: '" + email + "' fetched successfully.";
            } else {
                status = HttpStatus.NOT_FOUND;
                message = "Student data with guardian email: '" + email + "' not found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Failed to fetch student data with guardian email: " + email;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(new Response(message, student), status);
    }

    @GetMapping("find-by-guardian-id")
    public ResponseEntity<Response> findByGuardianEmail(@RequestParam Long id) {
        String message;
        HttpStatus status;
        Student student = null;

        try {
            student = studentService.findStudentByGuardianEmail(id);
            if (student != null) {
                status = HttpStatus.OK;
                message = "Student data with guardian id: " + id + " fetched successfully.";
            } else {
                status = HttpStatus.NOT_FOUND;
                message = "Student data with guardian id: " + id + " not found.";
            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "Failed to fetch student data with guardian id: " + id;
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return new ResponseEntity<>(new Response(message, student), status);
    }
}
