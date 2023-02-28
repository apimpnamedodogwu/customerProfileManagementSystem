package com.example.customerprofilemanagementsystem.data.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorAPIResponse {
    private String message;
    private boolean success;
}
