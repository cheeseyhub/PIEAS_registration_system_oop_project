package com.pieas.student_registration.Entities;

import java.util.ArrayList;

import org.springframework.data.annotation.Id;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SemesterEntity {
    @Id
    private String id;
    private int semesterNumber;
    private boolean enrolled;
    @NotEmpty
    private ArrayList<SubjectEntity> subjects;

    public SemesterEntity(ArrayList<SubjectEntity> subjects, boolean enrolled) {
        this.subjects = subjects;
        this.enrolled = enrolled;
    }

    public boolean isEnrolled() {
        return enrolled;
    }

    public void addSubject(SubjectEntity subject) {
        this.subjects.add(subject);
    }

}
