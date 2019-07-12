package org.acme.quickstart.entities;

import org.acme.quickstart.interceptor.ValidOrdine;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OrdineValidator implements ConstraintValidator<ValidOrdine, Ordine> {

    @Override
    public void initialize(ValidOrdine constraintAnnotation) {
    }

    @Override
    public boolean isValid(Ordine ordine, ConstraintValidatorContext context) {


        context.disableDefaultConstraintViolation();

        return true;
    }
}
