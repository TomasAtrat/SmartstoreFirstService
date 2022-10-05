package com.smartstore.smartstorewebservice.dataAccess.repositories;

import com.smartstore.smartstorewebservice.dataAccess.entities.Barcode;
import com.smartstore.smartstorewebservice.dataAccess.entities.Branch;
import com.smartstore.smartstorewebservice.dataAccess.entities.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {
    List<Stock> findAllByBarcodeBarcode(Optional<Barcode> barcodeObj);

    Stock findByBarcodeBarcodeAndBranch(Barcode barcode, Branch branch);
}