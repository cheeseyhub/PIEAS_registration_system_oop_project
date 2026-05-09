package com.pieas.student_registration.Entities;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "students")
public class StudentEntity {

    @Id
    private String id;

    private String name;

    @NotBlank
    private String password;

    @Indexed(unique = true)
    @Pattern(regexp = "\\d{2}-\\d{1}-\\d{1}-\\d{3}-\\d{4}", message = "Registration number must match format: XX-X-X-XXX-XXXX")
    private String registrationNumber;

    @NotBlank
    private String department;

    @NotBlank
    private String fatherName;
    @NotBlank
    private String contactNo;
    @NotBlank
    private String domicile;
    @NotBlank
    private String rollNo;
    @NotBlank
    private String dateOfBirth;
    @NotBlank
    private String cnic;
    @NotBlank
    private String pieasEmail;
    @NotBlank
    private String personalEmail;
    @NotBlank
    private String libraryId;
    @NotBlank
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
