package com.sixseven.sixsevenBank.auth_users.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true) // "Dont ask , Dont care"
public class ResetPasswordRequest { // specifically for receiving data in (from React)

    //will be used to request for forgot password
    private String email;

    //will be used to set new password
    private String code;
    private String newPassword; // temp place to hold Plain text before SpringBoot hashes it & saves it.
}

// why use a DTO here instead of the User Entity?
// its a key production-ready concept

// use User Entity : major problems : User doesn't have a code field or a newPassword field
