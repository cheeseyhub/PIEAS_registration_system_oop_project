package com.pieas.student_registration.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pieas.student_registration.Entities.StudentEntity;
import com.pieas.student_registration.Repositories.StudentRepository;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public Optional<StudentEntity> getStduentById(String id) {
        return studentRepository.findById(id);

    }

    public void addStudent(StudentEntity student) {
        studentRepository.insert(student);
    }

}
