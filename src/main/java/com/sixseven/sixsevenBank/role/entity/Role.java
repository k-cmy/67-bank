package com.sixseven.sixsevenBank.role.entity;

// Java Entity
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // ds table (数据库表)
@Data // auto creates getters and setters (自动创建getter和setter)
@Builder // lets u to create a new Role obj very cleanly , e.g. : Role.builder().name("ADMIN").build();
@Table(name = "roles")  // tells database to name the table roles (数据库表名)
@AllArgsConstructor // no param
@NoArgsConstructor //create the "Constructors" (构造函数) needed to initialize (初始化) the object. (初始化对象)
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


// Bulider needs AllArgsConstructor to work , since erm Builder always needs to pass fields values at once when .build
