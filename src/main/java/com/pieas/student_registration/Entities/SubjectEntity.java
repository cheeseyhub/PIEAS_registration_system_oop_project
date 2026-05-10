package com.pieas.student_registration.Entities;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectEntity {
    @Id
    private String id;
    @NotBlank(message = "Subject name must not be blank")
    private String courseTitle;

    private boolean isEnrolledInCourse = false;

    @NotBlank
    private String courseCode;

    @NotBlank
    private String instructor;

    @NotBlank
    private String duration;

    @DecimalMax(value = "4.0")
    @DecimalMin(value = "0.0")
    private double gpa;

    @Max(value = 100)
    @Min(value = 0)
    private int marks;

    @NotBlank
    private int creditHour;

    @Pattern(regexp = "^(A\\+|A-|A|B\\+|B-|B|C\\+|C-|C|D\\+|D-|D|F)$", message = "Grade must be a valid letter grade (e.g., A+, A, B-, F)")
    private String grade;

    public void calculteSubjectGpa() {
        if (marks >= 85) {
            this.setGrade("A+");
            this.setGpa(4.0);
        } else if (marks >= 80) {
            this.setGrade("A");
            this.setGpa(4.00);

        } else if (marks >= 75) {
            this.setGrade("B+");
            this.setGpa(3.67);

        } else if (marks >= 70) {
            this.setGrade("B");
            this.setGpa(3.33);
        } else if (marks >= 65) {
            this.setGrade("B-");
            this.setGpa(3);
        } else if (marks >= 60) {
            this.setGrade("C+");
            this.setGpa(2.67);
        } else if (marks >= 55) {
            this.setGrade("C");
            this.setGpa(2.33);
        } else if (marks >= 50) {
            this.setGrade("D");
            this.setGpa(2);
        }

    }

    public boolean isEnrolledInCourse() {
        return this.isEnrolledInCourse;
    }
}