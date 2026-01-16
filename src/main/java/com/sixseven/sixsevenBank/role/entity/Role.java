package com.sixseven.sixsevenBank.role.entity;

// Java Entity
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // ds table
@Data // auto creates getters and setters
@Builder // lets u to create a new Role obj very cleanly , e.g. : Role.builder().name("ADMIN").build();
@Table(name = "roles")  // tells database to name the table roles
@AllArgsConstructor
@NoArgsConstructor //create the "Constructors" needed to initialize the object.
public class Role {
    @Id // create an auto - incrementing ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true) // make sure dont accidentally create 2 roles called ADMIN
    @NotBlank(message = "Role Name is required") // validation
    private String name; // ROLE NAME : CUSTOMER , AUDITOR ADMIN
}

// how it fits into your CI/CD & react flow
// Spring boot (backend) : when run app , spring boot looks at this file and auto builds table in database

// React : when a user logs in , backend looks at this table to see if they are an ADMIN or CUSTOMER , it then sends that info to React so React knows whether to show the "Admin Dashboard" or just the "Balance" page

//CI/CD : in the pipeline , automated tests will use this file to make sure database logic is working correctly before the code is ever deployed to the real bank server.

