package com.example.customerprofilemanagementsystem.data.repositories;

import com.example.customerprofilemanagementsystem.data.models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findAdminByEmailIgnoreCase(String email);
}
