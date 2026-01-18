package com.sixseven.sixsevenBank.account.repo;

import com.sixseven.sixsevenBank.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Integer> {

    Optional<Account> findByAccountNumber(String accountNumber);
    List<Account> findByUserId (Long userId);
}
