package com.example.customerprofilemanagementsystem.controllers;

import com.example.customerprofilemanagementsystem.data.models.dto.ApiResponse;
import com.example.customerprofilemanagementsystem.data.models.dto.LoginRequest;
import com.example.customerprofilemanagementsystem.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RequestMapping("api/v1")
@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final TokenProvider tokenProvider;

    @PostMapping("/user/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        log.info("login method called with --> {}", loginRequest);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = tokenProvider.generateJWTToken(authentication);
        final HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("access-token", token);
        return new ResponseEntity<>(new ApiResponse("success", 200, true), responseHeaders, HttpStatus.OK);
    }
}