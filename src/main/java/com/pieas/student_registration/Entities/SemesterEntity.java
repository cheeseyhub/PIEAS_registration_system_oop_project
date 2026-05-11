package com.pieas.student_registration.Entities;

import java.util.ArrayList;

import javax.security.auth.Subject;

import org.springframework.data.annotation.Id;

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
    @Id
    private String id;
    private int semesterNumber;

    @NotBlank(message = "Semester Name must not be empty.")
    private String semesterName;
    private boolean enrolled;

    @NotEmpty
    private ArrayList<SubjectEntity> subjects;

    private double semesterGpa;

    public SemesterEntity(ArrayList<SubjectEntity> subjects, boolean enrolled) {
        this.subjects = subjects;
        this.enrolled = enrolled;
    }

    public boolean isEnrolled() {
        return enrolled;
    }

    public void addSubject(SubjectEntity subject) {
        this.subjects.add(subject);
    }

    public void removeSubject(SubjectEntity subject) {
        this.subjects.remove(subject);
    }

    public double calculateCoursegpa() {
        if (subjects.isEmpty() || subjects == null) {
            return 0.0;
        }
        double total = 0.0;

        for (SubjectEntity subject : subjects) {
            total += (subject.getGpa() * subject.getCreditHour());
        }
        double sumOfCreditHours = 0.0;
        for (SubjectEntity subject : subjects) {
            sumOfCreditHours += subject.getCreditHour();
        }

        total = total / sumOfCreditHours;
        return total;

    }

    public int getTotalCreditHour() {
        int totalSumOfCreditHour = 0;

        for (SubjectEntity subject : subjects) {
            totalSumOfCreditHour += subject.getCreditHour();
        }
        return totalSumOfCreditHour;

    }

    public int getTotalEnrolledSubjects() {
        return this.subjects.size();

    }

}
