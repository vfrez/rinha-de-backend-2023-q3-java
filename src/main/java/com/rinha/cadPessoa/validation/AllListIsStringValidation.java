package com.rinha.cadPessoa.validation;

import com.rinha.cadPessoa.validation.anotation.AllListIsString;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Objects;

public class AllListIsStringValidation implements ConstraintValidator<AllListIsString, List<Object>> {
    @Override
    public void initialize(AllListIsString constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Object> value, ConstraintValidatorContext context) {
        if (Objects.isNull(value)) {
            return true;
        }

        List<Object> stringsInList = value.stream().filter(a -> a instanceof String).toList();

        return stringsInList.size() == value.size();
    }
}
