package com.smartstore.smartstorewebservice.microservices.rfid.services;

import com.smartstore.smartstorewebservice.dataAccess.entities.Barcode;
import com.smartstore.smartstorewebservice.dataAccess.entities.EpcBarcode;
import com.smartstore.smartstorewebservice.dataAccess.repositories.BarcodeRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.EpcBarcodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpcBarcodeService {

    private EpcBarcodeRepository epcBarcodeRepository;
    private BarcodeRepository barcodeRepository;

    @Autowired
    public EpcBarcodeService(EpcBarcodeRepository epcBarcodeRepository,
                             BarcodeRepository barcodeRepository) {
        this.epcBarcodeRepository = epcBarcodeRepository;
        this.barcodeRepository = barcodeRepository;
    }

    public List<EpcBarcode> findAllEpcByBarcodeTopTen(Barcode barcode){
        if(barcodeRepository.existsById(barcode.getId()))
            return this.epcBarcodeRepository.findTop10ByBarcode(barcode);
        return null;
    }
}
