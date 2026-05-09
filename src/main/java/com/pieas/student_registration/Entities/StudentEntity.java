package com.pieas.student_registration.Entities;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
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
@Document(collection = "students")
public class StudentEntity {

    @Id
    private String id;

    @NotBlank(message = "The name of student must not be blank.")
    private String name;
    @DecimalMin(value = "0.0", message = "Percentage cannot be negative")
    @DecimalMax(value = "100.00", message = "Percentage cannot exceed 100.00")
    @NotBlank(message = "Passowrd cannot be blank")
    private String password;

    @Indexed(unique = true)
    @Pattern(regexp = "\\d{2}-\\d{1}-\\d{1}-\\d{3}-\\d{4}", message = "Registration number must match format: XX-X-X-XXX-XXXX")
    private String registrationNumber;

    @NotBlank(message = "The  department must not be blank")
    private String department;

    @NotBlank(message = "The fatherName must not be blank")
    private String fatherName;

    @NotBlank(message = "The contactNo must not be blank")
    private String contactNo;

    @NotBlank(message = "The domicile must not be blank")
    private String domicile;

    @NotBlank(message = "The rollNumber must not be blank")
    private String rollNo;

    @NotBlank(message = "The dateOfBirth must not be blank")
    private String dateOfBirth;

    @NotBlank(message = "The CNIC must not be blank")
    private String cnic;

    @NotBlank(message = "The pieasEmail must not be blank")
    @Email
    private String pieasEmail;

    @NotBlank(message = "The email must not be blank")
    @Email
    private String personalEmail;

    @NotBlank(message = "The libararyId must not be blank")
    private String libraryId;

    @NotBlank(message = "The address of student  must not be blank")
    private String address;

    private ArrayList<SemesterEntity> semesters;
    private ArrayList<DegreeEntity> certifications;

    public Optional<SemesterEntity> getSemster(int semesterNumber) {
        return Optional.of(this.semesters.get(semesterNumber));

    }

    public Optional<DegreeEntity> getDegree(int degreeIndex) {
        return Optional.of(this.certifications.get(degreeIndex));

    }
}
