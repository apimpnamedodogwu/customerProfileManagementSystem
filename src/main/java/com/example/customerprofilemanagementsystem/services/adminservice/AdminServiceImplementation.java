package com.example.customerprofilemanagementsystem.services.adminservice;

import com.example.customerprofilemanagementsystem.data.models.Admin;
import com.example.customerprofilemanagementsystem.data.repositories.AdminRepository;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {
    private final AdminRepository adminRepository;

    public Optional<Admin> findAdmin(String email) {
        return adminRepository.findAdminByEmailIgnoreCase(email);
    }
}
