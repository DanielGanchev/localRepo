package com.plannerapp.vallidation;



import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = StringDateInTheFutureValidator.class)

public @interface StringDateInTheFuture {

    String message() default "Date is not in future!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
