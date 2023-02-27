package com.example.customerprofilemanagementsystem.services.adminservice;

import com.example.customerprofilemanagementsystem.data.enums.ProductPlan;
import com.example.customerprofilemanagementsystem.data.models.dto.CustomerCreationRequest;
import com.example.customerprofilemanagementsystem.data.models.dto.CustomerResponse;
import com.example.customerprofilemanagementsystem.services.customerservice.CustomerService;
import com.example.customerprofilemanagementsystem.services.exceptions.ExistingCustomerException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AdminServiceImplementation implements AdminService {
    private final CustomerService customerService;

    @Override
    public void addACustomer(CustomerCreationRequest request) {
        customerService.createACustomer(request);
    }

    @Override
    public CustomerResponse updateCustomerDetail(long id, ProductPlan productPlan) throws ExistingCustomerException {
        return customerService.updateCustomerPlan(id, productPlan);
    }
}
