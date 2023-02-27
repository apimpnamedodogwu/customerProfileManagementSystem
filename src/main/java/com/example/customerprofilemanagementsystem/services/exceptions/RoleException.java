package com.example.customerprofilemanagementsystem.services.exceptions;

public class RoleException extends Exception {
    public RoleException(String role) {
        super(String.format(role + " is invalid!"));
    }
}
