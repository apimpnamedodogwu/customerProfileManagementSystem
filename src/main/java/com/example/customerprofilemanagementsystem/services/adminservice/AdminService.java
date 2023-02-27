package com.example.customerprofilemanagementsystem.services.adminservice;

import com.example.customerprofilemanagementsystem.data.enums.ProductPlan;
import com.example.customerprofilemanagementsystem.data.models.dto.CustomerCreationRequest;
import com.example.customerprofilemanagementsystem.data.models.dto.CustomerResponse;
import com.example.customerprofilemanagementsystem.services.exceptions.ExistingCustomerException;

public interface AdminService {
    void addACustomer(CustomerCreationRequest request);

    CustomerResponse updateCustomerDetail(long id, ProductPlan productPlan) throws ExistingCustomerException;
}
