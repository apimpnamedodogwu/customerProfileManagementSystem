package com.example.customerprofilemanagementsystem.data.models.dto;

import jakarta.ws.rs.sse.OutboundSseEvent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private String message;
    private int status;
    private boolean success;
    private String token;

}

