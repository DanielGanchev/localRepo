package com.dictionaryapp.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = StringDateInThePastOrPresentValidator.class)
public @interface StringDateInThePastOrPresent {

    String message() default "Date is not in future!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
