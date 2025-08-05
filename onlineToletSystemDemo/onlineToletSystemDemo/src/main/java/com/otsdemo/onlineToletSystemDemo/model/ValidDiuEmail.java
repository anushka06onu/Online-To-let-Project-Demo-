package com.otsdemo.onlineToletSystemDemo.model; // or validation if you prefer

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DiuEmailValidator.class)
public @interface ValidDiuEmail {
    String message() default "Email must be a valid DIU email (e.g., example@diu.edu.bd)";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
