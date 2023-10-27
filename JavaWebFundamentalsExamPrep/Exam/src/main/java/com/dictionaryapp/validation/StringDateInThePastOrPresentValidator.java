package com.dictionaryapp.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class StringDateInThePastOrPresentValidator implements ConstraintValidator<StringDateInThePastOrPresent,String> {


    @Override
    public void initialize(StringDateInThePastOrPresent constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (!value.isEmpty()) {
          if (LocalDate.parse(value).isBefore(LocalDate.now()) || LocalDate.parse(value).isEqual(LocalDate.now())){
              return true;
          }
        }
        return false;
    }
}
