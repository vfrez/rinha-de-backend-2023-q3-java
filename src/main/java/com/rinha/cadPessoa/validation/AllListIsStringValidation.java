package com.rinha.cadPessoa.validation;

import com.rinha.cadPessoa.validation.annotation.AllListIsString;
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
    public boolean isValid(List<Object> valueList, ConstraintValidatorContext context) {
        if (Objects.isNull(valueList)) {
            return true;
        }

        long stringsInList = valueList.stream()
                .filter(value -> value instanceof String)
                .count();

        return stringsInList == valueList.size();
    }
}
