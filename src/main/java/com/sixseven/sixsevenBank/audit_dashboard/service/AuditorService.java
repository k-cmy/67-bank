package com.sixseven.sixsevenBank.audit_dashboard.service;

import com.sixseven.sixsevenBank.account.dtos.AccountDTO;
import com.sixseven.sixsevenBank.auth_users.dtos.UserDTO;
import com.sixseven.sixsevenBank.transaction.dtos.TransactionDTO;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface AuditorService {

    Map<String, Long> getSystemTotals();

    Optional<UserDTO> findUserByEmail(String email);

    Optional<AccountDTO> findAccountDetailsByAccountNumber(String accountNumber);

    List<TransactionDTO> findTransactionsByAccountNumber(String accountNumber);

    Optional<TransactionDTO> findTransactionById(Long transactionId);
}