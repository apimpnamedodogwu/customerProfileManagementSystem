package com.example.customerprofilemanagementsystem.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

@RequiredArgsConstructor
public class DoesNotExistValidator implements ConstraintValidator<Exist, Object> {

    private final JdbcTemplate jdbcTemplate;
    private Exist exist;

    @Override
    public void initialize(Exist constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        exist = constraintAnnotation;
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (exist.nullable() && o == null) return true;
        if (o == null) return false;
        String table = exist.table();
        String columnName = exist.columnName();
        String query = exist.query();

        if (query == null || query.trim().isEmpty()){
            query = "SELECT COUNT(*) FROM %s WHERE %s = ?";
            query = String.format(query, table, columnName);
        }
        int count = Optional.ofNullable(jdbcTemplate.queryForObject(query, Integer.class, o)).orElse(0);
        return count==0;
}

}
