package com.smartstore.smartstorewebservice.microservices.inventory.validation;

import com.smartstore.smartstorewebservice.common.data.InventoryData;
import com.smartstore.smartstorewebservice.common.validation.IValidator;
import com.smartstore.smartstorewebservice.common.validation.validationRules.ExistProductValidationRule;
import com.smartstore.smartstorewebservice.common.validation.validationRules.ExistUserValidationRule;
import com.smartstore.smartstorewebservice.common.validation.validationRules.MaxStringLengthValidationRule;
import com.smartstore.smartstorewebservice.common.validation.validationRules.ValueGreaterThanValidationRule;
import com.smartstore.smartstorewebservice.dataAccess.entities.InventoryDetail;
import com.smartstore.smartstorewebservice.dataAccess.repositories.ProductRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.UserInfoRepository;

import java.util.ArrayList;
import java.util.List;

public class InventoryValidator implements IValidator {

    private List<String> errors;
    private InventoryData inventory;
    private ProductRepository productRepository;
    private UserInfoRepository userInfoRepository;

    public InventoryValidator(InventoryData inventory,
                              ProductRepository productRepository,
                              UserInfoRepository userInfoRepository) {
        this.inventory = inventory;
        this.productRepository = productRepository;
        this.userInfoRepository = userInfoRepository;
        this.errors = new ArrayList<>();
    }

    @Override
    public List<String> validate() {
        validateDescription();
        validateUserAssigned();
        inventory.getDetails().forEach(detail-> {
            validateSupposedQuantity(detail);
            validateReadQuantity(detail);
            validateAcceptedQuantity(detail);
            validateProduct(detail);
        });
        return errors;
    }

    private void validateDescription() {
        String maxLength = new MaxStringLengthValidationRule(inventory.getInventory().getDescription().length(),
                255).validate();

        if (maxLength.length() > 0) errors.add("Descripción: " + maxLength);
    }

    private void validateUserAssigned() {
        if (inventory.getInventory().getUserAssigned() != null) {
            String validUser = new ExistUserValidationRule(inventory.getInventory().getUserAssigned(), userInfoRepository).validate();
            if(validUser.length() > 0) errors.add("Usuario: " + validUser);
        }
    }

    private void validateSupposedQuantity(InventoryDetail detail) {
        if(detail.getSupposedQty() != null){
            String maxLength = new ValueGreaterThanValidationRule(detail.getSupposedQty(),
                    -1).validate();

            if (maxLength.length() > 0) errors.add("Cantidad teórica: " + maxLength);
        }
    }

    private void validateReadQuantity(InventoryDetail detail) {
        if(detail.getReadQty() != null){
            String maxLength = new ValueGreaterThanValidationRule(detail.getReadQty(),
                    -1).validate();

            if (maxLength.length() > 0) errors.add("Cantidad leída: " + maxLength);
        }
    }

    private void validateAcceptedQuantity(InventoryDetail detail) {
        if(detail.getAcceptedQty() != null){
            String maxLength = new ValueGreaterThanValidationRule(detail.getAcceptedQty(),
                    -1).validate();

            if (maxLength.length() > 0) errors.add("Cantidad aceptada: " + maxLength);
        }
    }

    private void validateProduct(InventoryDetail detail) {
        if (detail.getProductCode() != null) {
            String validProduct = new ExistProductValidationRule(detail.getProductCode(), productRepository).validate();
            if(validProduct.length() > 0) errors.add("Producto: " + validProduct);
        }
        else {
            errors.add("Se requiere ingresar un producto");
        }
    }
}
