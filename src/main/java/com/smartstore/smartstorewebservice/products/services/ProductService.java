package com.smartstore.smartstorewebservice.products.services;

import com.smartstore.smartstorewebservice.common.util.HasLogger;
import com.smartstore.smartstorewebservice.common.entities.Barcode;
import com.smartstore.smartstorewebservice.common.entities.Product;
import com.smartstore.smartstorewebservice.common.repositories.BarcodeRepository;
import com.smartstore.smartstorewebservice.common.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductService implements HasLogger {
    private ProductRepository productRepository;
    private BarcodeRepository barcodeRepository;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          BarcodeRepository barcodeRepository) {
        this.productRepository = productRepository;
        this.barcodeRepository = barcodeRepository;
    }

    @Transactional(rollbackOn = Exception.class)
    public boolean addProduct(Product product) {
        try {
            this.productRepository.save(product);
        } catch (Exception e) {
            getLogger().error("ERROR: " + e.getMessage(), e);
            return false;
        }
        return true;
    }

    @Transactional(rollbackOn = Exception.class)
    public boolean addBarcode(Barcode barcode) {
        try {
            if (isBarcodeValid(barcode)) createBarcodeAndInsertOrUpdateProduct(barcode);

            else throw new Exception("El registro ya existe para código de barras " + barcode.getId());

        } catch (Exception e) {
            getLogger().error("ERROR: " + e.getMessage(), e);
            return false;
        }
        return true;
    }

    private void createBarcodeAndInsertOrUpdateProduct(Barcode barcode) {
        if (!this.productRepository.existsById(barcode.getProduct().getId()))
            this.productRepository.save(barcode.getProduct());
        barcodeRepository.save(barcode);
    }

    private boolean isBarcodeValid(Barcode barcode) {
        return barcodeRepository.findById(barcode.getId()).get() == null || barcode.getProduct() != null;
    }
}
