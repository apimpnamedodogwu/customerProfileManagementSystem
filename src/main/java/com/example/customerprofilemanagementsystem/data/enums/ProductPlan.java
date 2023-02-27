package com.example.customerprofilemanagementsystem.data.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum ProductPlan {
    BASIC("B"),
    DIAMOND("D"),
    BRONZE("BR"),
    GOLD("G");
    private String code;


    ProductPlan(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


}