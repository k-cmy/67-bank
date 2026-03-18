package com.sixseven.sixsevenBank.account.services;

import com.sixseven.sixsevenBank.account.dtos.AccountDTO;
import com.sixseven.sixsevenBank.account.entity.Account;
import com.sixseven.sixsevenBank.auth_users.entity.User;
import com.sixseven.sixsevenBank.enums.AccountType;
import com.sixseven.sixsevenBank.res.Response;

import java.util.List;

public interface AccountService {
    Account createAccount(AccountType accountType, User user);

    Response<List<AccountDTO>> getMyAccounts();

    Response<?> closeAccount(String accountNumber);
}