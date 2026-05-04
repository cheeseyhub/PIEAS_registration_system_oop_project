package com.pieas.student_registration.Entities;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Document(collection = "students")
public class StudentEntity {
    @Id
    private String id;
    private String name;

    private String password;
    @Indexed(unique = true)
    @Pattern(regexp = "\\d{2}-\\d{1}-\\d{1}-\\d{3}-\\d{4}", message = "Registration number must match format: XX-X-X-XXX-XXXX")
    private String registrationNumber;
    @NotBlank
    private String department;

    private ArrayList<SemesterEntity> semesters;

    public ArrayList<SemesterEntity> getSemesters() {
        return semesters;
    }

    public void setSemesters(ArrayList<SemesterEntity> semesters) {
        this.semesters = semesters;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Optional<SemesterEntity> getSemster(int semesterNumber) {
        return Optional.of(this.semesters.get(semesterNumber));

    }
}
