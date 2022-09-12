package com.smartstore.smartstorewebservice.common.validation.validationRules;

import com.smartstore.smartstorewebservice.common.validation.IValidationRule;

public class ValueGreaterThanValidationRule implements IValidationRule {

    private int value;
    private int comparator;

    public ValueGreaterThanValidationRule(int value, int comparator) {
        this.value = value;
        this.comparator = comparator;
    }

    @Override
    public String validate() {
        String error = "";
        if(value <= comparator) error =  "El valor numérico debe ser superior a " + comparator;
        return error;
    }
}
