package com.smartstore.smartstorewebservice.microservices.orders.validation;

import com.smartstore.smartstorewebservice.common.validation.IValidator;
import com.smartstore.smartstorewebservice.common.validation.validationRules.MaxStringLengthValidationRule;
import com.smartstore.smartstorewebservice.dataAccess.entities.OrderInfo;

import java.util.ArrayList;
import java.util.List;

public class OrderValidator implements IValidator {
    private final ArrayList<String> errors;
    private OrderInfo orderInfo;

    public OrderValidator(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
        errors = new ArrayList<>();
    }

    @Override
    public List<String> validate() {
        validateDocument();
        validateFirstName();
        validateLastName();
        validateEmail();
        validatePhone();
        validateAddress();
        validateDescription1();
        validateDescription2();
        validateDescription3();
        validateDescription4();

        return this.errors;
    }

    private void validateDocument() {
        String maxLength = new MaxStringLengthValidationRule(orderInfo.getCustomer().getId().length(),
                64).validate();

        if (maxLength.length() > 0) errors.add("Documento: " +  maxLength);
    }

    private void validateFirstName() {
        String maxLength = new MaxStringLengthValidationRule(orderInfo.getCustomer().getName().length(),
                64).validate();

        if (maxLength.length() > 0) errors.add("Nombre: " +  maxLength);
    }

    private void validateLastName() {
        String maxLength = new MaxStringLengthValidationRule(orderInfo.getCustomer().getLastName().length(),
                64).validate();

        if (maxLength.length() > 0) errors.add("Apellido: " +  maxLength);
    }

    private void validateEmail() {
        String maxLength = new MaxStringLengthValidationRule(orderInfo.getCustomer().getEmail().length(),
                64).validate();

        if (maxLength.length() > 0) errors.add("Email: " +  maxLength);
    }

    private void validatePhone() {
        String maxLength = new MaxStringLengthValidationRule(orderInfo.getCustomer().getPhoneNumber().length(),
                64).validate();

        if (maxLength.length() > 0) errors.add("Teléfono: " +  maxLength);
    }

    private void validateAddress() {
        String maxLength = new MaxStringLengthValidationRule(orderInfo.getAddress().length(),
                255).validate();

        if (maxLength.length() > 0) errors.add("Dirección: " +  maxLength);
    }

    private void validateDescription1() {
        String maxLength = new MaxStringLengthValidationRule(orderInfo.getDescription1().length(),
                255).validate();

        if (maxLength.length() > 0) errors.add("Anexo 1: " +  maxLength);
    }

    private void validateDescription2() {
        String maxLength = new MaxStringLengthValidationRule(orderInfo.getDescription2().length(),
                255).validate();

        if (maxLength.length() > 0) errors.add("Anexo 2: " +  maxLength);
    }

    private void validateDescription3() {
        String maxLength = new MaxStringLengthValidationRule(orderInfo.getDescription3().length(),
                255).validate();

        if (maxLength.length() > 0) errors.add("Anexo 3: " +  maxLength);
    }

    private void validateDescription4() {
        String maxLength = new MaxStringLengthValidationRule(orderInfo.getDescription4().length(),
                255).validate();

        if (maxLength.length() > 0) errors.add("Anexo 4: " +  maxLength);
    }
}
