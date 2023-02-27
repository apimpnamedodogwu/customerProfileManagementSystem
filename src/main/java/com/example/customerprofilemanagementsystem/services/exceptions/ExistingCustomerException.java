package com.example.customerprofilemanagementsystem.services.exceptions;

public class ExistingCustomerException extends Exception {
    public ExistingCustomerException(String message) {
        super(String.format(message));
    }
}

