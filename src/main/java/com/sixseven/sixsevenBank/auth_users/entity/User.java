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

    @ManyToMany(fetch = FetchType.EAGER) //A user can have many roles (e.g., they are a Customer and an Admin), and a role can belong to many users
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @OneToMany (mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts;

    // set auto to now when user is born in ds
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updateAt; // useful for seeing when a user last changed their password/profile



}
