package com.example.customerprofilemanagementsystem.services.exceptions;

public class AdminDoesNotExistException extends Exception {
    public AdminDoesNotExistException(String message) {
        super(String.format(message));
    }
}
