package com.smartstore.smartstorewebservice.microservices.stock.services;

import com.smartstore.smartstorewebservice.dataAccess.entities.Barcode;
import com.smartstore.smartstorewebservice.dataAccess.entities.Stock;
import com.smartstore.smartstorewebservice.dataAccess.repositories.BarcodeRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {
    private StockRepository stockRepository;
    private BarcodeRepository barcodeRepository;

    @Autowired
    public StockService(StockRepository stockRepository,
                        BarcodeRepository barcodeRepository) {
        this.stockRepository = stockRepository;
        this.barcodeRepository = barcodeRepository;
    }

    public List<Stock> getStockByBarcode(String barcode) {
        Optional<Barcode> barcodeObj = this.barcodeRepository.findById(barcode);
        if (barcodeObj.isPresent())
            return this.stockRepository.findAllByBarcodeBarcode(barcodeObj);

        return null;
    }
}
