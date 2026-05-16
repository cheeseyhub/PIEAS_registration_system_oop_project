package com.pieas.student_registration.Entities;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "departments")
public class DepartmentEntity {
    @Id
    private String id;

    @NotBlank(message = "department name must not be empty")
    private String departmentName;

    List<@Pattern(regexp = "^(BS|MS|PHD)$", message = "Department must be BS or MS followed by CIS, ME, MME, CE, PHY, or EE (e.g., 'BS CIS' or 'MS ME')") String> degreeTitle;

    List<String> degreeName;

}
