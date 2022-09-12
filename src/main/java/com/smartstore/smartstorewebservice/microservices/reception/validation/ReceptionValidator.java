package com.smartstore.smartstorewebservice.microservices.reception.validation;

import com.smartstore.smartstorewebservice.common.data.Reception;
import com.smartstore.smartstorewebservice.common.validation.IValidator;
import com.smartstore.smartstorewebservice.common.validation.validationRules.ExistProductValidationRule;
import com.smartstore.smartstorewebservice.common.validation.validationRules.ExistUserValidationRule;
import com.smartstore.smartstorewebservice.common.validation.validationRules.MaxStringLengthValidationRule;
import com.smartstore.smartstorewebservice.common.validation.validationRules.ValueGreaterThanValidationRule;
import com.smartstore.smartstorewebservice.dataAccess.entities.ReceptionDetail;
import com.smartstore.smartstorewebservice.dataAccess.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class ReceptionValidator implements IValidator {
    private final ArrayList<String> errors;
    private Reception reception;
    private ProductRepository productRepository;

    public ReceptionValidator(Reception reception, ProductRepository productRepository){
        this.reception = reception;
        this.productRepository = productRepository;
        this.errors = new ArrayList<>();
    }

    @Override
    public List<String> validate() {
        validateDescription();

        reception.getDetails().forEach(i->{
            validateScheduledQuantity(i);
            validateReceivedQuantity(i);
            validateProduct(i);
        });

        return errors;
    }

    private void validateProduct(ReceptionDetail detail) {
        if (detail.getProductCode() != null) {
            String validProduct = new ExistProductValidationRule(detail.getProductCode(), productRepository).validate();
            if(validProduct.length() > 0) errors.add("Producto: " + validProduct);
        }
        else {
            errors.add("Se requiere ingresar un producto");
        }
    }

    private void validateScheduledQuantity(ReceptionDetail detail) {
        String maxLength = new ValueGreaterThanValidationRule(detail.getScheduledQty(),
                0).validate();

        if (maxLength.length() > 0) errors.add("Cantidad agendada: " + maxLength);
    }

    private void validateReceivedQuantity(ReceptionDetail detail) {
        if(detail.getReceivedQty() != null){
            String maxLength = new ValueGreaterThanValidationRule(detail.getReceivedQty(),
                    -1).validate();

            if (maxLength.length() > 0) errors.add("Cantidad recibida: " + maxLength);
        }
    }

    private void validateDescription() {
        String maxLength = new MaxStringLengthValidationRule(reception.getReceptionList().getDescription().length(),
                255).validate();

        if (maxLength.length() > 0) errors.add("Descripción: " + maxLength);
    }
}
