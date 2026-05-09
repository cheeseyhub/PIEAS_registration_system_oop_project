package com.pieas.student_registration.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "courses")
public class CourseEntity {
    @Id
    private String id;
    @NotBlank
    private String courseName;
    @NotBlank
    private String department;

}
