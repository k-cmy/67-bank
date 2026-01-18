package com.sixseven.sixsevenBank.auth_users.repo;

import com.sixseven.sixsevenBank.auth_users.entity.PasswordResetCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// by extending JpaRepository<PasswordResetCode, Long> auto gets the ability to sae new codes and check if they exist
public interface PasswordResetCodeRepo extends JpaRepository<PasswordResetCode, Long> {

    // LookUp function :
    // when its used : when a user receives an email with a code like XYZ-123 and types it into React , the backend uses this method to check the database
    Optional<PasswordResetCode> findByCode(String code);

   // clean up function :
    // in a bank app : you dont want old , used reset codes sitting in database forever. Once the pwd is successfully changed, you want to wipe that code out for security
    void deleteByCode(String code);
}

//        Why this matters for CI/CD
//        When you are testing your application in a CI/CD pipeline, you will often write "Integration Tests." These tests will:
//
//        Use the Repo to save a fake code.
//
//        Try to "find" it to make sure the database is connected.
//
//        Delete it to make sure the cleanup logic works. If any of these fail, the CI/CD pipeline stops the deployment, ensuring that your "Forgot Password" feature is never broken in Production.