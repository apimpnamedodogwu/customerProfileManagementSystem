package com.example.customerprofilemanagementsystem.data.repositories;

import com.example.customerprofilemanagementsystem.data.models.Customer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findAllBy(Pageable pageable);
    Optional<Customer> findCustomerById(Long id);
}
