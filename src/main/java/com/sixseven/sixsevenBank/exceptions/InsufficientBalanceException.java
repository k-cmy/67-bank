package com.sixseven.sixsevenBank.exceptions;

public class InsufficientBalanceException extends RuntimeException {

public InsufficientBalanceException(String error) {
        super(error);
    }
}
