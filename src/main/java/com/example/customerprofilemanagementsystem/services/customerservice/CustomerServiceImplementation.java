package com.example.customerprofilemanagementsystem.services.customerservice;

import com.example.customerprofilemanagementsystem.data.enums.ProductFeature;
import com.example.customerprofilemanagementsystem.data.enums.ProductPlan;
import com.example.customerprofilemanagementsystem.data.models.Customer;
import com.example.customerprofilemanagementsystem.data.models.dto.CustomerCreationRequest;
import com.example.customerprofilemanagementsystem.data.models.dto.CustomerResponse;
import com.example.customerprofilemanagementsystem.data.repositories.CustomerRepository;
import com.example.customerprofilemanagementsystem.services.exceptions.ExistingCustomerException;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CustomerServiceImplementation implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public void createACustomer(CustomerCreationRequest request) {
        Customer customer = new Customer();
        BeanUtils.copyProperties(request, customer);
        customer.setProductFeature(setProductPlan(request.getProductPlan()));
        customerRepository.save(customer);

    }

    private Set<ProductFeature> setProductPlan(ProductPlan productPlan) {
        Set<ProductFeature> productFeatures = new HashSet<>();
        switch (productPlan) {
            case BASIC:
                productFeatures.add(ProductFeature.LAW_REPORT);
                productFeatures.add(ProductFeature.ANALYTICS);
                break;
            case DIAMOND:
                productFeatures.add(ProductFeature.LAW_REPORT);
                productFeatures.add(ProductFeature.ANALYTICS);
                productFeatures.add(ProductFeature.STATE_LAWS);
                break;
            case BRONZE:
                productFeatures.add(ProductFeature.LAW_REPORT);
                productFeatures.add(ProductFeature.ANALYTICS);
                productFeatures.add(ProductFeature.STATE_LAWS);
                productFeatures.add(ProductFeature.COUNSEL_PROFILING);
                break;
            case GOLD:
                productFeatures.add(ProductFeature.LAW_REPORT);
                productFeatures.add(ProductFeature.ANALYTICS);
                productFeatures.add(ProductFeature.STATE_LAWS);
                productFeatures.add(ProductFeature.COUNSEL_PROFILING);
                productFeatures.add(ProductFeature.ADVANCED_SEARCH);
            default:
                return productFeatures;
        }
        return productFeatures;
    }

    @Override
    public List<CustomerResponse> customers(int pageNumber, int size) {
        List<Customer> customers = customerRepository.findAllBy(PageRequest.of(pageNumber - 1, size));
        List<CustomerResponse> customerResponses = new ArrayList<>();
        customers.forEach(customer -> {
            CustomerResponse customerResponse = new CustomerResponse();
            BeanUtils.copyProperties(customer, customerResponse);
            customerResponses.add(customerResponse);
        });
        return customerResponses;
    }

    @Override
    public CustomerResponse updateCustomerPlan(long id, ProductPlan productPlan) throws ExistingCustomerException {
        Optional<Customer> existingUser = customerRepository.findCustomerById(id);
        if (existingUser.isPresent()) {
            existingUser.get().setProductPlan(productPlan);
            existingUser.get().setProductFeature(setProductPlan(productPlan));
            Customer updatedCustomer = customerRepository.save(existingUser.get());
            CustomerResponse customerResponse = new CustomerResponse();
            BeanUtils.copyProperties(updatedCustomer, customerResponse);
            return customerResponse;
        }
        throw new ExistingCustomerException("Customer with id " + id + " does not exist.");
    }

}



