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
@Document(collection = "admins")
public class AdminEntity {
    @Id
    private String id;

    @NotBlank
    @Unique
    private String username;
    @NotBlank
    private String password;

    public AdminEntity(@NotBlank @Unique String username, @NotBlank String password) {
        this.username = username;
        this.password = password;
    }

}
