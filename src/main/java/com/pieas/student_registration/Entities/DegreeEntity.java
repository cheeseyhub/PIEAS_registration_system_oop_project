package com.pieas.student_registration.Entities;

import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Document
public class DegreeEntity {
    @NotBlank
    private String degreeName;
    @NotBlank
    private String nameOfInstitute;
    @NotBlank
    private int yearOfPassing;
    @NotBlank
    private String subject;
    @NotBlank
    private String grade;

    @DecimalMax("100.00")
    private double marksPercentage;

    private int totalMarks;
    private int obtainedMarks;

    @DecimalMax("4.00")
    private double cgpa;

    private String postionInBoard;

}
