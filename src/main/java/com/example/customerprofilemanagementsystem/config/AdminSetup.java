package com.example.customerprofilemanagementsystem.config;

import com.example.customerprofilemanagementsystem.data.enums.Role;
import com.example.customerprofilemanagementsystem.data.models.Admin;
import com.example.customerprofilemanagementsystem.data.repositories.AdminRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminSetup {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AdminRepository adminRepository;

    @PostConstruct
    public void createAdmin(){
      Admin admin =  adminRepository.findAdminByEmailIgnoreCase("eden@yahoo.com").orElse(new Admin());
        admin.setEmail("eden@yahoo.com");
        admin.setFirstName("Tolani");
        admin.setFirstName("Asake");
        admin.setRole(Role.ADMIN);
        admin.setPassword(bCryptPasswordEncoder.encode("1234"));
        adminRepository.save(admin);
    }
}
