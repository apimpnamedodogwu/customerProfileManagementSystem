package com.example.customerprofilemanagementsystem.data.models.dto;

import com.example.customerprofilemanagementsystem.data.enums.ProductPlan;
import com.example.customerprofilemanagementsystem.validator.Exist;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class CustomerCreationRequest {
    @NotNull(message = "First name field cannot be null.")
    @NotEmpty(message = "First ame field cannot be empty.")
    private String firstName;
    @NotNull(message = "Last name field cannot be null.")
    @NotEmpty(message = "Last name field cannot be empty.")
    private String lastName;
    private String phoneNumber;
    @Email
    @Exist(table = "customers", columnName = "email", message = "{email.already.exists}")
    private String email;
    private ProductPlan productPlan;

}
