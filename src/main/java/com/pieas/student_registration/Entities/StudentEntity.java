package com.pieas.student_registration.Entities;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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

    // @NotBlank(message = "The name of student must not be blank.")
    private String name;

    // @NotBlank(message = "The gender field must not be blank")
    @Pattern(regexp = "^(male|female)$")
    private String gender;

    @NotBlank(message = "Passowrd cannot be blank")
    private String password;

    public boolean isAllowedToEdit = false;

    @Indexed(unique = true)
    // @NotBlank
    @Pattern(regexp = "\\d{2}-\\d{1}-\\d{1}-\\d{3}-\\d{4}", message = "Registration number must match format: XX-X-X-XXX-XXXX")
    private String registrationNumber;

    // Department must in the format BS/MS max three letter word.
    @NotBlank(message = "The department must not be blank")
    @Pattern(regexp = "^(BS|MS) (CIS|ME|MME|CE|PHY|EE|NE)$", message = "Department must be BS or MS followed by CIS, ME, MME, CE, PHY, or EE (e.g., 'BS CIS' or 'MS ME')")
    private String department;

    // @NotBlank(message = "The fatherName must not be blank")
    private String fatherName;

    // @NotBlank(message = "The contactNo must not be blank")
    private String contactNo;

    // @NotBlank(message = "The emergencyContact must not be blank")
    private String emergencyContact;

    // @NotBlank(message = "The domicile must not be blank")
    private String domicile;

    // @NotBlank(message = "The rollNumber must not be blank")
    private String rollNo;

    // @NotBlank(message = "The dateOfBirth must not be blank")
    private String dateOfBirth;

    // @NotBlank(message = "The CNIC must not be blank")
    private String cnic;

    // @NotBlank(message = "The pieasEmail must not be blank")
    @Email
    private String pieasEmail;

    // @NotBlank(message = "The email must not be blank")
    @Email
    private String personalEmail;

    // @NotBlank(message = "The libararyId must not be blank")
    private String libraryId;

    // @NotBlank(message = "The address of student must not be blank")
    private String address;

    public StudentEntity(@NotBlank(message = "Passowrd cannot be blank") String password,
            @NotBlank @Pattern(regexp = "\\d{2}-\\d{1}-\\d{1}-\\d{3}-\\d{4}", message = "Registration number must match format: XX-X-X-XXX-XXXX") String registrationNumber,
            @NotBlank(message = "The department must not be blank") @Pattern(regexp = "^(BS|MS) (CIS|ME|MME|CE|PHY|EE|NE)$", message = "Department must be BS or MS followed by CIS, ME, MME, CE, PHY, or EE (e.g., 'BS CIS' or 'MS ME')") String department,
            String address) {
        this.password = password;
        this.registrationNumber = registrationNumber;
        this.department = department;
        this.address = address;
    }

    private double cgpa;

    private ArrayList<SemesterEntity> semesters;
    private ArrayList<CertificationEntity> certifications;

    public Optional<SemesterEntity> getSemster(int semesterNumber) {
        return Optional.of(this.semesters.get(semesterNumber));

    }

    public Optional<CertificationEntity> getDegree(int degreeIndex) {
        return Optional.of(this.certifications.get(degreeIndex));

    }

    public double calculateCgpa() {
        if (semesters == null || semesters.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        for (SemesterEntity semester : semesters) {
            total += semester.getSemesterGpa();
        }

        this.cgpa = total / semesters.size();
        return this.cgpa;
    }

    public SemesterEntity getCurrentSemester() {
        return this.semesters.getLast();

    }

    public int getCurrentSemesterIndex() {
        if (semesters == null || semesters.isEmpty()) {
            return 0;
        }

        return this.getSemesters().size() - 1;
    }
}
