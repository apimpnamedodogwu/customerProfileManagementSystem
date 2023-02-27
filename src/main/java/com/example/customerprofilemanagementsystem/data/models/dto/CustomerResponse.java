package com.example.customerprofilemanagementsystem.data.models.dto;

import com.example.customerprofilemanagementsystem.data.enums.ProductFeature;
import com.example.customerprofilemanagementsystem.data.enums.ProductPlan;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
public class CustomerResponse {
    private String firstName;
    private String lastName;
    private ProductPlan productPlan;
    private Set<ProductFeature> productFeature;

}
