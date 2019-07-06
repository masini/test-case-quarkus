package org.acme.quickstart.entities;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BarLayoutValidator implements ConstraintValidator<ValidBarLayout, BarLayout> {
    @Override
    public boolean isValid(BarLayout barLayout, ConstraintValidatorContext constraintValidatorContext) {

        System.err.println("validator");

        //con has app la fascia fine freddi deve essere specificata
        if(barLayout.isHasApp() && barLayout.getFineFasciaFreddi()==null){
            return false;
        }
        //Non e' possibile settare hasApp se il layout e' front
        if(FlussoServizio.FRONT.equals(barLayout.getFlusso())&&  barLayout.isHasApp()){
            return false;
        }

        if(barLayout.getTolleranza()< 1){
            return false;
        }

        if(barLayout.getMaxComandeVassoio()< 4){
            return false;
        }
        return true;
    }
}
