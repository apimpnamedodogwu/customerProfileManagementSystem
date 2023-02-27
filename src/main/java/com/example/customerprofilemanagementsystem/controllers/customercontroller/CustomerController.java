package com.example.customerprofilemanagementsystem.controllers.customercontroller;

import com.example.customerprofilemanagementsystem.data.enums.ProductPlan;
import com.example.customerprofilemanagementsystem.data.models.dto.CustomerCreationRequest;
import com.example.customerprofilemanagementsystem.data.models.dto.CustomerResponse;
import com.example.customerprofilemanagementsystem.services.customerservice.CustomerServiceImplementation;
import com.example.customerprofilemanagementsystem.services.exceptions.ExistingCustomerException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/customer")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerServiceImplementation customerServiceImplementation;


    @PostMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> addACustomer(@Valid @RequestBody CustomerCreationRequest request) {
        customerServiceImplementation.createACustomer(request);
        String message = "A customer has been created successfully!";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<List<CustomerResponse>> listOFCurrentCustomers(@RequestParam int pageNumber, @RequestParam int pageSize) {
        List<CustomerResponse> list = customerServiceImplementation.customers(pageNumber, pageSize);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping("")
    public ResponseEntity<CustomerResponse> updateCustomerPlan(@RequestParam long id, @RequestParam ProductPlan productPlan) throws ExistingCustomerException {
        CustomerResponse customerResponse = customerServiceImplementation.updateCustomerPlan(id, productPlan);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }
}
