package com.pieas.student_registration.Entities;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;

public class SubjectEntity {
    @Id
    private String id;
    @NotBlank
    private String subjetName;
    private double gpa;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubjetName() {
        return subjetName;
    }

    public void setSubjetName(String subjetName) {
        this.subjetName = subjetName;
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

}
