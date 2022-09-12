package com.smartstore.smartstorewebservice.common.validation.validationRules;

import com.smartstore.smartstorewebservice.common.validation.IValidationRule;
import com.smartstore.smartstorewebservice.dataAccess.entities.Product;
import com.smartstore.smartstorewebservice.dataAccess.repositories.ProductRepository;

public class ExistProductValidationRule implements IValidationRule {

    private Product productCode;
    private ProductRepository productRepository;

    public ExistProductValidationRule(Product productCode, ProductRepository productRepository) {
        this.productCode = productCode;
        this.productRepository = productRepository;
    }

    @Override
    public String validate() {
        String error = "";
        if(!productRepository.existsById(productCode.getId()))
            error = "El producto " + productCode.getId() + " no existe en el sistema";
        return error;
    }
}
