package com.pieas.student_registration.Entities;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;

public class SubjectEntity {
    @Id
    private String id;
    @NotBlank
    private String subjectName;
    private double gpa;

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

}
