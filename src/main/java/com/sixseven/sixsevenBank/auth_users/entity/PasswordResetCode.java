package com.sixseven.sixsevenBank.auth_users.entity;

import com.sixseven.sixsevenBank.auth_users.repo.UserRepo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.sixseven.sixsevenBank.auth_users.entity.User;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "password_reset_code")
@AllArgsConstructor
@NoArgsConstructor

public class PasswordResetCode { // a specialized Security Entity
    // Handle Forgot Password

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code;

    // 1 pwd reset request belongs to exactly 1 user
    //fetch = FetchType.EAGER: : Since security operations need to be fast, we tell Spring Boot: When you find the code, grab the User's details (like their email) immediately so we know who we are helping
    @OneToOne(targetEntity =  User.class, fetch = FetchType.EAGER)
    // nullable = false: This is a database rule , cant have a reset code floating ard that isnt attached to a real human being
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    private LocalDateTime expiryDate; // crucial for Production. you don't want a reset code to work forever. usually set it to like 15/30 mins

    private boolean used;

}
//how this file interacts with React, Spring Boot, and the Database

//React: A user clicks "Forgot Password" and enters their email.
//
//Spring Boot: * Generates a random string (the code).
//
//Creates a new PasswordResetCode object.
//
//Connects it to the User.
//
//Sets an expiryDate (e.g., now() + 15 mins).
//
//Database: Saves this record in the passowrd_reset_code table.
//
//The Email: Spring Boot sends the code to the user's email.
//
//React: User types the code into a form.
//
//Spring Boot: Checks the database. If the code exists, is NOT used, and is NOT expired, it allows the password change.
