package com.example.customerprofilemanagementsystem.services.exceptions;

public class IsAnAdminException extends Exception {
    public IsAnAdminException(String message) {
        super(String.format(message));
    }
}
