package com.sixseven.sixsevenBank.auth_users.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sixseven.sixsevenBank.role.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

//DTO : Data Transfer Object = blueprint for the JSON that travels over the Internet to React

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//control how Java Obj is converted into the text (JSON) that React can read
@JsonInclude(JsonInclude.Include.NON_NULL) // if a field is empty , it is removed from the JSON entirely, makes the data package smaller & faster to send.
@JsonIgnoreProperties(ignoreUnknown = true) // if React sends back more data than we asked for , Spring Boot will just ignore the extra stuff instead of crashing

public class UserDTO {

    private Long id;
    private String firstName;

    private String lastName;
    private String phoneNumber;

    private String email;

    @JsonIgnore // CRITICAL FOR SECURITY , ensures it nvr sent to the browser (Inspect Element tab)
    private String password;

    private String profilePictureUrl;
    private boolean active;

    private List<Role> roles;

    @JsonManagedReference // it helps avoid recursion loop by ignoring the userDTO withing the AccountDTO
    private List <AccountDTO> accounts;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
// How this helps your CI/CD & Deployment

// when deploy the app, API Documentation (like Swagger) reads this UserDTO to tell other developers exactly what data they can expect

// React asks : give me user # 1
// 2. Spring Boot : fetches the User Entity from the database
// 3. ModelMapper : copies the data from Entity to this UserDTO
//4. Jackson turns UserDTO into JSON
// 5.React receives a clean safe package of data
