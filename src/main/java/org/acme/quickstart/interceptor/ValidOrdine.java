package org.acme.quickstart.interceptor;

import org.acme.quickstart.entities.OrdineValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = OrdineValidator.class)
@Target( { TYPE, METHOD, FIELD, PARAMETER })
@Retention(RUNTIME)
public @interface ValidOrdine {
    String message() default "{it.esselunga.ordbar.shared.validators.ValidOrdine}";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
