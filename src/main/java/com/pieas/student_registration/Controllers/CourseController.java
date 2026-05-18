package com.pieas.student_registration.Controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pieas.student_registration.Entities.CourseEntity;
import com.pieas.student_registration.Services.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public ArrayList<CourseEntity> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public CourseEntity getCourseById(@PathVariable String id) {
        return courseService.getCourseById(id).orElse(null);
    }

    @GetMapping("/department/{department}")
    public ArrayList<CourseEntity> getCoursesByDepartment(@PathVariable String department) {
        return courseService.getCoursesByDegreeProgram(department);
    }

    @PostMapping
    public CourseEntity addCourse(@Valid @RequestBody CourseEntity course) {
        return courseService.addCourse(course);
    }

    @PutMapping("/{id}")
    public CourseEntity updateCourse(@PathVariable String id, @Valid @RequestBody CourseEntity course) {
        course.setId(id);
        return courseService.updateCourse(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable String id) {
        courseService.deleteCourse(id);
    }
}
