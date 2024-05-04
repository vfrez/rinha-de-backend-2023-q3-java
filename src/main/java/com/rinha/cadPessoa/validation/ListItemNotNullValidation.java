package com.rinha.cadPessoa.validation;

import com.rinha.cadPessoa.validation.annotation.ListItemNotNull;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Objects;

public class ListItemNotNullValidation implements ConstraintValidator<ListItemNotNull, List<Object>> {

    @Override
    public void initialize(ListItemNotNull constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Object> valueList, ConstraintValidatorContext context) {
        if (Objects.isNull(valueList)) {
            return true;
        }

        long invalidCounter = valueList.stream()
                .filter(value -> Objects.isNull(value))
                .count();

        return !(invalidCounter >= 1);
    }
}
