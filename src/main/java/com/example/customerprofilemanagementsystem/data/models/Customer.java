package com.example.customerprofilemanagementsystem.data.models;

import com.example.customerprofilemanagementsystem.data.enums.ProductFeature;
import com.example.customerprofilemanagementsystem.data.enums.ProductPlan;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private  String phoneNumber;
    @Column(unique = true)
    private String email;
    private ProductPlan productPlan;
    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<ProductFeature> productFeature;
}
