package com.sixseven.sixsevenBank.auth_users.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class RegistrationRequest {// another Request DTO, specifically designed for the Sign-Up process.

    @NotBlank(message = "FirstName is required")
    private String firstName;

    private String lastName;

    private String phoneNumber;

    @NotBlank(message = "Email is required")
    @Email
    private String email;

    private List<String> roles;

    @NotBlank(message = "Password is required")
    private String password;
}