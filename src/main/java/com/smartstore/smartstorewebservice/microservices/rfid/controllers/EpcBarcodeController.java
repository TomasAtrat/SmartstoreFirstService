package com.smartstore.smartstorewebservice.microservices.rfid.controllers;

import com.smartstore.smartstorewebservice.common.wrappers.ListOfEpc;
import com.smartstore.smartstorewebservice.common.wrappers.ListOfInventories;
import com.smartstore.smartstorewebservice.dataAccess.entities.Barcode;
import com.smartstore.smartstorewebservice.microservices.rfid.services.EpcBarcodeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/epc")
@AllArgsConstructor
public class EpcBarcodeController {
    private EpcBarcodeService epcBarcodeService;

    @GetMapping("/top-ten/{barcode}")
    public ListOfEpc findTopTenByBarcode(@PathVariable String barcode) {
        Barcode barcodeEntity = new Barcode();
        barcodeEntity.setId(barcode);
        return new ListOfEpc(this.epcBarcodeService.findAllEpcByBarcodeTopTen(barcodeEntity));
    }

}
