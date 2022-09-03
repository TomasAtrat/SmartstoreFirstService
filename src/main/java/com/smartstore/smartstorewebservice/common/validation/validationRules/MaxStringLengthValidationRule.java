package com.smartstore.smartstorewebservice.common.validation.validationRules;

import com.smartstore.smartstorewebservice.common.validation.IValidationRule;

public class MaxStringLengthValidationRule implements IValidationRule {
    private int currentLength;
    private int max;

    public MaxStringLengthValidationRule(int currentLength, int max){
        this.currentLength = currentLength;
        this.max = max;
    }


    @Override
    public String validate() {
        String error = "";
        if(currentLength > max) error =  "El largo de la cadena de texto supera el máximo permitido de " + max;
        return error;
    }
}
