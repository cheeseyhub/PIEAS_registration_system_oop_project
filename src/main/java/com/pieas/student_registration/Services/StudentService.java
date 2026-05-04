package com.pieas.student_registration.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mongodb.DuplicateKeyException;
import com.pieas.student_registration.Entities.SemesterEntity;
import com.pieas.student_registration.Entities.StudentEntity;
import com.pieas.student_registration.Entities.SubjectEntity;
import com.pieas.student_registration.Repositories.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Optional<StudentEntity> getStduentById(String id) {
        return studentRepository.findById(id);

    }

    public void addStudent(StudentEntity student) {
        String encodedPassword = passwordEncoder.encode(student.getPassword());
        student.setPassword(encodedPassword);
        if (student.getSemesters() == null) {
            student.setSemesters(new ArrayList<>());
        }
        try {
            studentRepository.insert(student);
        } catch (DuplicateKeyException e) {
            System.err.println(e.getMessage());
        }
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

    public boolean authenticateUser(String department, String registrationNumber, String password) {
        // Validate registration number format
        if (!registrationNumber.matches("\\d{2}-\\d{1}-\\d{1}-\\d{3}-\\d{4}")) {
            return false;
        }
        
        Optional<StudentEntity> student = studentRepository.findByRegistrationNumber(registrationNumber);
        if (student.isPresent() && passwordEncoder.matches(password, student.get().getPassword())) {
            return true;
        }
        return false;

    }

    public Optional<StudentEntity> getStudentByRegistration(String registrationNo) {
        return studentRepository.findByRegistrationNumber(registrationNo);

    }

    public String addSubject(String registrationNumber, int semesterNumber, SubjectEntity subject) {
        Optional<StudentEntity> studentObject = studentRepository.findByRegistrationNumber(registrationNumber);

        if (studentObject.isEmpty()) {
            return "Student with registration number " + registrationNumber + " not found.";
        }

        StudentEntity student = studentObject.get();
        if (student.getSemesters() == null) {
            student.setSemesters(new ArrayList<>());
        }

        Optional<SemesterEntity> semester = student.getSemesters().stream()
                .filter(s -> s.getSemesterNumber() == semesterNumber)
                .findFirst();

        SemesterEntity semesterEntity;
        if (semester.isEmpty()) {
            semesterEntity = new SemesterEntity(new ArrayList<>(), true);
            semesterEntity.setSemesterNumber(semesterNumber);
            student.getSemesters().add(semesterEntity);
        } else {
            semesterEntity = semester.get();
        }

        if (semesterEntity.getSubjects() == null) {
            semesterEntity.setSubjects(new ArrayList<>());
        }

        semesterEntity.addSubject(subject);
        studentRepository.save(student);

        return "Subject added to semester " + semesterNumber + " for student " + registrationNumber + ".";
    }

}
