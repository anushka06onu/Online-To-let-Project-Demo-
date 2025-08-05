package com.otsdemo.onlineToletSystemDemo.model;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class DiuEmailValidator implements ConstraintValidator<ValidDiuEmail, String> {

    private static final String DIU_DOMAIN = "diu.edu.bd";

    @Override
    public void initialize(ValidDiuEmail constraintAnnotation) {
        // Initialization, if needed
    }

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null || email.isEmpty()) {
            return false;
        }

        // Check basic email format and domain
        try {
            String[] parts = email.split("@");
            if (parts.length != 2) return false;

            return parts[1].equals(DIU_DOMAIN);
        } catch (Exception e) {
            return false;
        }
    }
}