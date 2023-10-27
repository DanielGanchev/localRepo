package com.plannerapp.vallidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class StringDateInTheFutureValidator implements ConstraintValidator<StringDateInTheFuture,String> {


    @Override
    public void initialize(StringDateInTheFuture constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value != null) {
            LocalDate parse = LocalDate.parse(value);
            return parse.isAfter(LocalDate.now());
        }
        return false;
    }
}
