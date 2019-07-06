package org.acme.quickstart.entities;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;


@Target( { TYPE, METHOD, FIELD, PARAMETER })
@Constraint(validatedBy = BarLayoutValidator.class)
@Retention(RUNTIME)
public @interface ValidBarLayout {

    String message() default "BarLayout non valido";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
