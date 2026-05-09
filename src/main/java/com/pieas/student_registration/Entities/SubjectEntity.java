package com.pieas.student_registration.Entities;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotBlank;
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
    private String subjectName;
    private double gpa;

    public SubjectEntity(@NotBlank String subjectName, double gpa) {
        this.subjectName = subjectName;
        this.gpa = gpa;
    }

}