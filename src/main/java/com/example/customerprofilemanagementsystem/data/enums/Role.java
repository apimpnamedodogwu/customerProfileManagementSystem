package com.example.customerprofilemanagementsystem.data.enums;

import com.example.customerprofilemanagementsystem.services.exceptions.RoleException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.stream.Stream;

public enum Role {
        CUSTOMER("C"),
        ADMIN("A");
        private String type;

        Role(String type) {
            this.type = type;
        }

        @JsonCreator
        public static Role decode(final String type) throws RoleException {
            return Stream.of(Role.values()).filter(targetEnum -> targetEnum.type.equals(type)).findFirst().orElseThrow(() -> new RoleException("Role type is invalid!"));
        }

        @JsonValue
        public String getType() {
            return type;
        }
    }

