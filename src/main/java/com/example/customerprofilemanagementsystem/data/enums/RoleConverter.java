package com.example.customerprofilemanagementsystem.data.enums;


import com.example.customerprofilemanagementsystem.services.exceptions.RoleException;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoleConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role role) {
        if (role == null) {
            return null;
        }
        return role.getType();
    }

    @Override
    public Role convertToEntityAttribute(String type) {
        if (type == null) {
            return null;
        }
        try {
            return Role.decode(type);
        } catch (RoleException e) {
            throw new RuntimeException(e);
        }
    }
}

