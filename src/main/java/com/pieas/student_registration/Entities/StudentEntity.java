package com.pieas.student_registration.Entities;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotEmpty;

@Document(collection = "students")
public class StudentEntity {
    @Id
    private String id;
    private String name;
    private String registrationNumber;
    private String department;
    @NotEmpty
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
}
