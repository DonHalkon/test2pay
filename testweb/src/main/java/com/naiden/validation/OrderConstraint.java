package com.naiden.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OrderValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderConstraint {
    String message() default "Amount cann't be greater 5000 USD!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
