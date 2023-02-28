package com.example.customerprofilemanagementsystem.services.customerservice;

import com.example.customerprofilemanagementsystem.data.enums.ProductPlan;
import com.example.customerprofilemanagementsystem.data.models.dto.CustomerCreationRequest;
import com.example.customerprofilemanagementsystem.data.models.dto.CustomerResponse;
import com.example.customerprofilemanagementsystem.services.exceptions.ExistingCustomerException;
import com.example.customerprofilemanagementsystem.services.exceptions.IsAnAdminException;

import java.util.List;

public interface CustomerService {
    void createACustomer(CustomerCreationRequest request) throws IsAnAdminException;

    List<CustomerResponse> customers(int pageNumber, int size);
    CustomerResponse updateCustomerPlan(long id, ProductPlan productPlan) throws ExistingCustomerException;
}
