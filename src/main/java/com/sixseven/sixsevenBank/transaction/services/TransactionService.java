package com.sixseven.sixsevenBank.transaction.services;

import com.sixseven.sixsevenBank.res.Response;
import com.sixseven.sixsevenBank.transaction.dtos.TransactionDTO;
import com.sixseven.sixsevenBank.transaction.dtos.TransactionRequest;

import java.util.List;

public interface TransactionService {
    Response<?> createTransaction(TransactionRequest transactionRequest);
    Response<List<TransactionDTO>> getTransactionsForMyAccount(String accountNumber, int page, int size);
}
