package com.pieas.student_registration.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "courses")
public class CourseEntity {
    @Id
    private String id;
    @NotBlank(message = "course name must not be blank")
    private String courseName;

    @NotBlank(message = "department name must not be blank")
    private String degreeProgram;

    @NotBlank
    private String instructor;
    private String courseCode;
    private int semesterNo;
    private int creditHour;

    public CourseEntity(@NotBlank(message = "course name must not be blank") String courseName,
            @NotBlank(message = "degree program name must not be blank") String degreeProgram,
            @NotBlank String instructor,
            String courseCode, int semesterNo, int creditHour) {
        this.courseName = courseName;
        this.degreeProgram = degreeProgram;
        this.instructor = instructor;
        this.courseCode = courseCode;
        this.semesterNo = semesterNo;
        this.creditHour = creditHour;
    }

}
