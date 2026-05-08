package com.pieas.student_registration.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pieas.student_registration.Entities.StudentEntity;
import com.pieas.student_registration.Entities.SubjectEntity;
import com.pieas.student_registration.Services.StudentService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public void registerStudent(@Valid @RequestBody StudentEntity entity) {
        studentService.addStudent(entity);
    }

    @PostMapping("/authenticate")
    public boolean authenticateStudent(@Valid @RequestBody StudentEntity entity) {
        return studentService.authenticateUser(entity.getDepartment(), entity.getRegistrationNumber(),
                entity.getPassword());
    }

    @PostMapping("/{registrationNumber}/{semesterNumber}")
    public String addSubject(@PathVariable String registrationNumber,
            @PathVariable int semesterNumber,
            @RequestBody SubjectEntity subject) {
        return studentService.addSubject(registrationNumber, semesterNumber, subject);
    }

}
