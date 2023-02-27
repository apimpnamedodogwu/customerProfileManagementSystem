package com.example.customerprofilemanagementsystem.security;

import com.example.customerprofilemanagementsystem.data.enums.Role;
import com.example.customerprofilemanagementsystem.data.models.Admin;
import com.example.customerprofilemanagementsystem.data.repositories.AdminRepository;
import com.example.customerprofilemanagementsystem.services.exceptions.AdminDoesNotExistException;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserAuthService implements UserDetailsService {
    private final AdminRepository adminRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = null;
        try {
            admin = adminRepository.findAdminByEmailIgnoreCase(email).orElseThrow(() -> new AdminDoesNotExistException("Admin not found!"));
        } catch (AdminDoesNotExistException e) {
            log.error(e.getMessage());
        }
        return new org.springframework.security.core.userdetails.User(admin.getEmail(), admin.getPassword(), getAuthorities(admin.getRole()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Role role) {
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }
}
