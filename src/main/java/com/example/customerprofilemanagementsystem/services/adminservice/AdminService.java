package com.example.customerprofilemanagementsystem.services.adminservice;

import com.example.customerprofilemanagementsystem.data.enums.ProductPlan;
import com.example.customerprofilemanagementsystem.data.models.Admin;
import com.example.customerprofilemanagementsystem.data.models.dto.CustomerCreationRequest;
import com.example.customerprofilemanagementsystem.data.models.dto.CustomerResponse;
import com.example.customerprofilemanagementsystem.services.exceptions.ExistingCustomerException;
import com.example.customerprofilemanagementsystem.services.exceptions.IsAnAdminException;

import java.util.Optional;

public interface AdminService {
    Optional<Admin> findAdmin(String email);
}
