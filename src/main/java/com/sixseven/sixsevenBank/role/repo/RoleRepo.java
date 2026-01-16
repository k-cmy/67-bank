package com.sixseven.sixsevenBank.role.repo;

import com.sixseven.sixsevenBank.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// inheriting a massive amount of "Superpowers" without writing a single line of SQL
public interface RoleRepo extends JpaRepository<Role ,Long> {
    // Save a role: roleRepo.save(adminRole);
    //Delete a role: roleRepo.delete(adminRole);
    //Find all roles: roleRepo.findAll();
    //Find by ID: roleRepo.findById(1L);

    // a derived query , SpringBoot sees findBy + Name

    // looks at Role entity , sees a field called 'name'
    // auto writes SQL : SELECT * FROM roles WHERE name = ?;

    Optional<Role> findByName (String name);

    // What is Optional<Role>? = Production-Ready" safety feature

    // if role exist , contains the ROLE
    // if nt , box Empty

    // this forces to check if (role.isPresent()) before using it , prevents  bank app from crashing in the middle of a transaction.


}
