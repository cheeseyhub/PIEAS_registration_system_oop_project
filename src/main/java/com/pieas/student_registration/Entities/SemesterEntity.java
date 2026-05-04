package com.pieas.student_registration.Entities;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotEmpty;

public class SemesterEntity {
    @Id
    private String id;
    private int semesterNumber;
    private boolean enrolled;
    @NotEmpty
    private ArrayList<SubjectEntity> subjects;

    public SemesterEntity(ArrayList<SubjectEntity> subjects, boolean enrolled) {
        this.subjects = subjects;
        this.enrolled = enrolled;
    }

    public String getId() {
        return id;
    }

    public int getSemesterNumber() {
        return semesterNumber;
    }

    public void setSemesterNumber(int semesterNumber) {
        this.semesterNumber = semesterNumber;
    }

    public boolean isEnrolled() {
        return enrolled;
    }

    public void setEnrolled(boolean enrolled) {
        this.enrolled = enrolled;
    }

    public ArrayList<SubjectEntity> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<SubjectEntity> subjects) {
        this.subjects = subjects;
    }

    public void addSubject(SubjectEntity subject) {
        this.subjects.add(subject);
    }

}
