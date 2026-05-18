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
import com.pieas.student_registration.Exceptions.NotLoggedIn;
import com.pieas.student_registration.Repositories.StudentRepository;
import com.vaadin.flow.server.VaadinSession;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public Optional<StudentEntity> getStudentById(String id) {

        return studentRepository.findById(id);

    }

    public void deleteStudent(String registrationNo) {
        studentRepository.deleteByRegistrationNumber(registrationNo);

    }

    public void addStudent(StudentEntity student) throws IllegalArgumentException {
        String encodedPassword = passwordEncoder.encode(student.getPassword());
        student.setPassword(encodedPassword);
        if (student.getSemesters() == null) {
            student.setSemesters(new ArrayList<>());
        }
        try {
            studentRepository.insert(student);
        } catch (DuplicateKeyException e) {
            throw new IllegalArgumentException(
                    "Student with registration number" + student.getRegistrationNumber() + " is already registered");
        }
    }

    public ArrayList<StudentEntity> getAllStudents() {

        ArrayList<StudentEntity> students = new ArrayList<>(studentRepository.findAll());
        return students;

    }

    public String enrollInSemester(String id, int semesterNumber) {

        Optional<StudentEntity> student = this.getStudentById(id);

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

    public boolean authenticateUser(String degreeProgram, String registrationNumber, String password) {
        if (!registrationNumber.matches("\\d{2}-\\d{1}-\\d{1}-\\d{3}-\\d{4}")) {
            return false;
        }

        Optional<StudentEntity> student = studentRepository.findByRegistrationNumber(registrationNumber);
        if (student.isPresent()
                &&
                passwordEncoder.matches(password, student.get().getPassword())
                &&
                student.get().getDegreeName().equals(degreeProgram)) {
            return true;
        }
        return false;

    }

    public StudentEntity getStudentByRegistration(String registrationNo) {
        StudentEntity student = studentRepository.findByRegistrationNumber(registrationNo).orElse(null);
        return student;

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

    public void storeStudentData(String reg) {
        StudentEntity student = this.getStudentByRegistration(reg);
        VaadinSession.getCurrent().setAttribute("studentRegistrationNo", student.getRegistrationNumber());
    }

    public boolean checkStudentLoggedIn() {

        String studentRegistrationNumber = (String) VaadinSession.getCurrent().getAttribute("studentRegistrationNo");

        if (studentRegistrationNumber != null && !studentRegistrationNumber.isBlank()) {
            return true;

        } else {
            return false;
        }
    }

    public String getLoggedStudentRegistrationNo() throws NotLoggedIn {
        String studentRegistrationNo = (String) VaadinSession.getCurrent().getAttribute("studentRegistrationNo");
        if (studentRegistrationNo != null && !studentRegistrationNo.isBlank()) {
            return studentRegistrationNo;
        } else {
            throw new NotLoggedIn("No Student is logged in ");
        }

    }

    public String getLoggedStudentName() throws NotLoggedIn {
        String studentRegistrationNo = (String) VaadinSession.getCurrent().getAttribute("studentRegistrationNo");

        if (this.checkStudentLoggedIn()) {
            String studentName = getStudentByRegistration(studentRegistrationNo).getName();
            return studentName;
        } else {
            throw new NotLoggedIn("No Student is logged in ");

        }

    }

    public String setNewPassword(String newPassword) throws IllegalArgumentException {

        String studentRegistrationNo = (String) VaadinSession.getCurrent().getAttribute("studentRegistrationNo");
        if (studentRegistrationNo.isEmpty() || studentRegistrationNo.isBlank()) {
            throw new IllegalArgumentException("Student not logged in");

        }
        StudentEntity student = this.getStudentByRegistration(studentRegistrationNo);

        student.setPassword(passwordEncoder.encode(newPassword));
        studentRepository.save(student);
        return "Password changed succesfully";
    }

    public StudentEntity getLoggedUser() throws NotLoggedIn {
        String studentRegistrationNo = (String) VaadinSession.getCurrent().getAttribute("studentRegistrationNo");

        if (this.checkStudentLoggedIn()) {
            StudentEntity student = getStudentByRegistration(studentRegistrationNo);
            return student;
        } else {
            throw new NotLoggedIn("No Student is logged in ");

        }

    }

    public void updateStudent(StudentEntity student) throws IllegalArgumentException {
        String studentRegistrationNo = (String) VaadinSession.getCurrent().getAttribute("studentRegistrationNo");
        if (studentRegistrationNo.isEmpty() || studentRegistrationNo.isBlank()) {
            throw new IllegalArgumentException("Student not logged in");

        }

        studentRepository.save(student);
    }

}
