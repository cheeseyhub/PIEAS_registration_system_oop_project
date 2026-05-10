package com.pieas.student_registration.Entities;

import org.springframework.data.mongodb.core.mapping.Document;

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
@Document
public class CertificationEntity {
    @NotBlank(message = "Degree Name is required.")
    private String degreeName;
    @NotBlank(message = "Institute is required.")
    private String nameOfInstitute;

    @Min(value = 1950, message = "Year of passing cannot be too old.")
    @Max(value = 2030, message = "Year of passing cannot be far into the future.")
    private int yearOfPassing;

    @NotBlank(message = "Subject Name is required.")
    private String subject;

    @NotBlank(message = "Grade is required ")
    @Pattern(regexp = "^(A\\+|A-|A|B\\+|B-|B|C\\+|C-|C|D\\+|D-|D|F)$", message = "Grade must be a valid letter grade (e.g., A+, A, B-, F)")
    private String grade;

    @DecimalMin(value = "0.0", message = "Percentage cannot be negative")
    @DecimalMax(value = "100.00", message = "Percentage cannot exceed 100.00")
    private double marksPercentage;

    @Min(value = 1, message = "Total marks must be greater than 0")
    private int totalMarks;
    @Min(value = 0, message = "Obtained marks cannot be negative")
    private int obtainedMarks;

    @DecimalMin(value = "0.00", message = "The cgpa cannot be below negative")
    @DecimalMax(value = "4.00", message = "The cgpa must be above 4.00")
    private double cgpa;

    private String positionInBoard;

}
