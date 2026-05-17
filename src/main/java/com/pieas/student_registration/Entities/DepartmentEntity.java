package com.pieas.student_registration.Entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.vaadin.copilot.shaded.checkerframework.common.aliasing.qual.Unique;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "departments")
public class DepartmentEntity {
    @Id
    private String id;

    @NotBlank(message = "department name must not be empty")
    @Unique
    private String departmentName;

    List<@Pattern(regexp = "^(BS|MS|PHD)$", message = "Department must be BS or MS followed by CIS, ME, MME, CE, PHY, or EE (e.g., 'BS CIS' or 'MS ME')") String> degreeTitle;

    List<String> degreeName;

    public DepartmentEntity(@NotBlank(message = "department name must not be empty") String departmentName,
            List<@Pattern(regexp = "^(BS|MS|PHD)$", message = "Department must be BS or MS followed by CIS, ME, MME, CE, PHY, or EE (e.g., 'BS CIS' or 'MS ME')") String> degreeTitle,
            List<String> degreeName) {
        this.departmentName = departmentName;
        this.degreeTitle = degreeTitle;
        this.degreeName = degreeName;
    }
}
