package com.pieas.student_registration.Entities;

import java.util.ArrayList;

import org.springframework.data.mongodb.core.mapping.DocumentReference;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SemesterEntity {
    private int semesterNumber;

    @NotBlank(message = "Semester Name must not be empty.")
    private String semesterName;
    private boolean enrolled;

    @NotEmpty
    @DocumentReference
    private ArrayList<CourseEntity> courses;

    private double semesterGpa;

    public SemesterEntity(ArrayList<CourseEntity> courses, boolean enrolled) {
        this.courses = courses;
        this.enrolled = enrolled;
    }

    public boolean isEnrolled() {
        return enrolled;
    }

    public void addSubject(CourseEntity course) {
        this.courses.add(course);
    }

    public void removeSubject(CourseEntity course) {
        this.courses.remove(course);
    }

    public double calculateCoursegpa() {
        if (courses.isEmpty() || courses == null) {
            return 0.0;
        }
        double total = 0.0;

        for (CourseEntity subject : courses) {
            total += (subject.getGpa() * subject.getCreditHour());
        }
        double sumOfCreditHours = 0.0;
        for (CourseEntity subject : courses) {
            sumOfCreditHours += subject.getCreditHour();
        }

        total = total / sumOfCreditHours;
        return total;

    }

    public int getTotalCreditHour() {
        int totalSumOfCreditHour = 0;

        for (CourseEntity subject : courses) {
            totalSumOfCreditHour += subject.getCreditHour();
        }
        return totalSumOfCreditHour;

    }

    public int getTotalEnrolledcourses() {
        return this.courses.size();

    }

}
