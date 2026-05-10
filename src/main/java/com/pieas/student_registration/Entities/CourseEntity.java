package com.pieas.student_registration.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "courses")
public class CourseEntity {
    @Id
    private String id;
    @NotBlank(message = "course name must not be blank")
    private String courseName;
    @NotBlank(message = "department name must not be blank")
    private String department;
    @NotBlank
    private String courseCode;

}
