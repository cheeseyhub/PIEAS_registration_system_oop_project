package com.pieas.student_registration.Entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.vaadin.copilot.shaded.checkerframework.common.aliasing.qual.Unique;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "admins")
public class AdminEntity {
    @Id
    private String id;

    @Unique
    private String adminId;

    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
