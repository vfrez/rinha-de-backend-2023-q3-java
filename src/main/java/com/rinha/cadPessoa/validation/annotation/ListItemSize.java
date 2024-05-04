package com.rinha.cadPessoa.validation.annotation;

import com.rinha.cadPessoa.validation.ListItemSizeValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ListItemSizeValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ListItemSize {
    String message() default "There are items with invalid sizes in the list";

    int min() default 0;
    int max() default Integer.MAX_VALUE;

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
