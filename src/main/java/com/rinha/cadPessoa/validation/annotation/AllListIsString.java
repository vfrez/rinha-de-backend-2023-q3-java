package com.rinha.cadPessoa.validation.annotation;

import com.rinha.cadPessoa.validation.AllListIsStringValidation;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = AllListIsStringValidation.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AllListIsString {
    String message() default "There are non-String items in the list";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
