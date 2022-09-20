package com.smartstore.smartstorewebservice.common.validation.validationRules;

import com.smartstore.smartstorewebservice.common.validation.IValidationRule;
import com.smartstore.smartstorewebservice.dataAccess.entities.Barcode;
import com.smartstore.smartstorewebservice.dataAccess.repositories.BarcodeRepository;

public class ExistBarcodeValidationRule implements IValidationRule {

    private Barcode barcode;
    private BarcodeRepository barcodeRepository;

    public ExistBarcodeValidationRule(Barcode barcode, BarcodeRepository barcodeRepository) {
        this.barcode = barcode;
        this.barcodeRepository = barcodeRepository;
    }

    @Override
    public String validate() {
        String error = "";
        if(!barcodeRepository.existsById(barcode.getId()))
            error = "El código de barras " + barcode.getId() + " no existe en el sistema";
        return error;
    }
}
