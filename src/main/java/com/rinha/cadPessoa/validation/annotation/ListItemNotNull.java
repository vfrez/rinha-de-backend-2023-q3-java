package com.rinha.cadPessoa.validation.annotation;

import com.rinha.cadPessoa.validation.ListItemNotNullValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ListItemNotNullValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ListItemNotNull {
    String message() default "There are null items in the list";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
