package com.smartstore.smartstorewebservice.microservices.stock.controllers;

import com.smartstore.smartstorewebservice.common.wrappers.ListOfStock;
import com.smartstore.smartstorewebservice.microservices.stock.services.StockService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/stock")
@AllArgsConstructor
public class StockController {

    private StockService stockService;

    @GetMapping("/{barcode}")
    public ListOfStock getStockByBarcode(@PathVariable String barcode) {
        return new ListOfStock(this.stockService.getStockByBarcode(barcode));
    }
}
