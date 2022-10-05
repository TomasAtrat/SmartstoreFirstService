package com.smartstore.smartstorewebservice.dataAccess.repositories;

import com.smartstore.smartstorewebservice.dataAccess.entities.Barcode;
import com.smartstore.smartstorewebservice.dataAccess.entities.EpcBarcode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpcBarcodeRepository extends JpaRepository<EpcBarcode, String> {
    List<EpcBarcode> findTop10ByBarcode(Barcode barcode);
}