package com.rinha.cadPessoa.validation;

import com.rinha.cadPessoa.validation.annotation.ListItemSize;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.Objects;

public class ListItemSizeValidation implements ConstraintValidator<ListItemSize, List<Object>> {

    private int min;
    private int max;

    @Override
    public void initialize(ListItemSize constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.max = constraintAnnotation.max();

        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List<Object> valueList, ConstraintValidatorContext context) {
        if (Objects.isNull(valueList)) {
            return true;
        }

        long invalidCounter = valueList.stream()
                .filter(Objects::nonNull)
                .filter(value -> value.toString().length() > this.max)
                .filter(value -> value.toString().length() > this.min)
                .count();

        return !(invalidCounter >= 1);
    }
}
