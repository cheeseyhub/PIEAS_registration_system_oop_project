package com.pieas.student_registration.Controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pieas.student_registration.Entities.StudentEntity;
import com.pieas.student_registration.Services.StudentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @GetMapping("/all")
    public ArrayList<StudentEntity> getAllStudents(@RequestParam String param) {
        return studentService.getAllStudents();
    }

    @PostMapping
    public void registerStudent(@RequestBody StudentEntity entity) {

        studentService.addStudent(entity);
    }

}
