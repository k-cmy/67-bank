package com.sixseven.sixsevenBank.auth_users.entity;

import com.sixseven.sixsevenBank.role.entity.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;
    private String phoneNumber;

    @Email // ensures whatever user types into the React form actually looks like an email address
    @Column (unique = true, nullable = false)
    @NotBlank(message = "Email is required")
    private String email;

    private String passward;
    private String profilePictureUrl;
    private boolean active = true; // instead of deleting a user , u set this to false "freeze" their account.

    //A user can have many roles (e.g., they are a Customer and an Admin), and a role can belong to many users
    @ManyToMany(fetch = FetchType.EAGER) // dont want to wait when checking if a user has permission to transfer money
    @JoinTable( // Ds can't easily link Many to many so SpringBoot creates a Helper Table (a spreadsheet with just 2 columns : user_id & role_id) to keep track of who is what
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    // 1 person can have mutiple bank accounts (Saving/Current)
    //mappedBy : tell Spring that the Account entity is the one in charge of tracking this relationship
    // cascade : if dlt this User , dlt all thier bank accounts too. It prevents "ghost data" from cluttering database.
    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts;

    // set auto to now when user is born in ds
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updateAt; // useful for seeing when a user last changed their password/profile



}
// How this works with React & CI/CD

// User Sign-up : React sends the firstName , lastName & email . SpringBoot uses @Builder to create the User obj and sets createdAt auto.

//Login Security : The List <Role> roles is checked. if contains Admin, the React frontend shows the "Admin Panel" button.

// Production Update: if u add a new field (like middleName) to this file , CI/CD pipeline (using a tool like Liquibase/Hiberate) will automatically update the real database table without losing existing user data.


