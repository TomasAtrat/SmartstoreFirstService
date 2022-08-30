package com.smartstore.smartstorewebservice.microservices.products.controllers;

import com.smartstore.smartstorewebservice.dataAccess.entities.Barcode;
import com.smartstore.smartstorewebservice.dataAccess.entities.Product;
import com.smartstore.smartstorewebservice.microservices.products.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/")
    public boolean addProduct(@RequestBody final Product product) {
        return productService.addProduct(product);
    }

    @PostMapping("/barcodes/")
    public boolean addProduct(@RequestBody final Barcode barcode) {
        return productService.addBarcode(barcode);
    }

}
