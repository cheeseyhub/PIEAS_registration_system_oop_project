package com.pieas.student_registration.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pieas.student_registration.Entities.StudentEntity;
import com.pieas.student_registration.Services.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public void registerStudent(@RequestBody StudentEntity entity) {

        studentService.addStudent(entity);
    }

    @PostMapping("/authenticate")
    public boolean authenticateStudent(@RequestBody StudentEntity entity) {

        return studentService.authenticateUser(entity.getRegistrationNumber(), entity.getPassword());
    }

}
