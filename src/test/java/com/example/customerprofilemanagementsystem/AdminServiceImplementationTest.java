package com.example.customerprofilemanagementsystem;

import com.example.customerprofilemanagementsystem.controllers.customercontroller.CustomerController;
import com.example.customerprofilemanagementsystem.data.enums.ProductPlan;
import com.example.customerprofilemanagementsystem.data.models.Customer;
import com.example.customerprofilemanagementsystem.data.models.dto.CustomerCreationRequest;
import com.example.customerprofilemanagementsystem.data.models.dto.CustomerResponse;
import com.example.customerprofilemanagementsystem.data.repositories.CustomerRepository;
import com.example.customerprofilemanagementsystem.services.customerservice.CustomerServiceImplementation;

import com.example.customerprofilemanagementsystem.services.exceptions.ExistingCustomerException;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class AdminServiceImplementationTest {


    CustomerRepository customerRepository = mock(CustomerRepository.class);
    CustomerServiceImplementation customerService = new CustomerServiceImplementation(customerRepository);
    CustomerController customerController = new CustomerController(customerService);


    @Test
    void testThatAdminCanRegisterACustomer() {
        CustomerCreationRequest request = CustomerCreationRequest.builder()
                .email("edenelenwoke@gmail.com")
                .firstName("Eden")
                .lastName("Elenwoke")
                .build();
        request.setProductPlan(ProductPlan.BASIC);
        when(customerRepository.save(any(Customer.class))).thenReturn(new Customer());
        customerController.addACustomer(request);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void testThatAdminCanGetCurrentCustomerList() {
        List<Customer> customers = new ArrayList<>();
        Customer customer = new Customer();
        customers.add(customer);
        when(customerRepository.findAllBy(any(Pageable.class))).thenReturn(customers);
        ResponseEntity<List<CustomerResponse>> response = customerController.listOFCurrentCustomers(1, 5);
        List<CustomerResponse> customerResponses = response.getBody();
        assertEquals(customerResponses.size(), customers.size());
        verify(customerRepository, times(1)).findAllBy(any(Pageable.class));
    }

    @Test
    void testThatAdminCanUpdateCustomerPlan() throws ExistingCustomerException {
        ProductPlan productPlan = ProductPlan.GOLD;
        long userId = 1L;
        Customer customer = new Customer();
        customer.setProductPlan(ProductPlan.BASIC);
        when(customerRepository.findCustomerById(anyLong())).thenReturn(Optional.of(customer));
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        ResponseEntity<CustomerResponse> response = customerController.updateCustomerPlan(userId, productPlan);
        CustomerResponse customerResponse = response.getBody();
        assertEquals(customerResponse.getProductFeature().size(), 5);
    }

    @Test
    void testThatAdminCanUpdateCustomerPlanThrowsException() {
        ProductPlan productPlan = ProductPlan.GOLD;
        long userId = 1L;
        Customer customer = new Customer();
        customer.setProductPlan(ProductPlan.BASIC);
        when(customerRepository.findCustomerById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ExistingCustomerException.class, () -> customerController.updateCustomerPlan(userId, productPlan));
    }

}
