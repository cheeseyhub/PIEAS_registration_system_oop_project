package com.pieas.student_registration.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pieas.student_registration.Entities.CourseEntity;
import com.pieas.student_registration.Repositories.CourseRepository;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public ArrayList<CourseEntity> getAllCourses() {
        return new ArrayList<>(courseRepository.findAll());
    }

    public Optional<CourseEntity> getCourseById(String id) {
        return courseRepository.findById(id);
    }

    public CourseEntity addCourse(CourseEntity course) {
        return courseRepository.insert(course);
    }

    public void deleteCourse(String id) {
        courseRepository.deleteById(id);
    }

    public CourseEntity updateCourse(CourseEntity course) {
        return courseRepository.save(course);
    }

    public ArrayList<CourseEntity> getCoursesByDepartment(String department) {
        return new ArrayList<>(courseRepository.findByDepartment(department));
    }

}
