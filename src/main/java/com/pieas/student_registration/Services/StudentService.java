package com.pieas.student_registration.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pieas.student_registration.Entities.SemesterEntity;
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

    public ArrayList<StudentEntity> getAllStudents() {

        ArrayList<StudentEntity> students = new ArrayList<>(studentRepository.findAll());
        return students;

    }

    public String enrollInSemester(String id, int semesterNumber) {

        Optional<StudentEntity> student = this.getStduentById(id);

        if (student.isPresent()) {
            ArrayList<SemesterEntity> semesters = student.get().getSemesters();
            if (semesters == null) {
                return "The Studnet has no semester Records Initialized";
            }
            semesters.forEach(semester -> {
                if (semester.getSemesterNumber() == semesterNumber) {
                    semester.setEnrolled(true);
                }
            });
            studentRepository.save(student.get());
            return "Student with the id " + id + " is Now enrolled in the semester " + semesterNumber;
        } else {
            return "Could not find the student with the id " + id;
        }

    }

}
