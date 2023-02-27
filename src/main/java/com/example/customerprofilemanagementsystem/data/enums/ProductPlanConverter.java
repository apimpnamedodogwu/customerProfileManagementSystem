package com.example.customerprofilemanagementsystem.data.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import jakarta.persistence.RollbackException;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class ProductPlanConverter implements AttributeConverter<ProductPlan, String> {
    @Override
    public String convertToDatabaseColumn(ProductPlan productPlan) {
        if (productPlan == null) {
            return null;
        }
        return productPlan.getCode();
    }

    @Override
    public ProductPlan convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }
        return Stream.of(ProductPlan.values())
                .filter(product -> product.getCode().equals(code))
                .findFirst()
                .orElseThrow(RollbackException::new);
    }
}
