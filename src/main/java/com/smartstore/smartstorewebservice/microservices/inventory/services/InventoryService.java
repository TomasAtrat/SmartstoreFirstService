package com.smartstore.smartstorewebservice.microservices.inventory.services;

import com.smartstore.smartstorewebservice.common.data.InventoryData;
import com.smartstore.smartstorewebservice.common.wrappers.HTTPAnswer;
import com.smartstore.smartstorewebservice.dataAccess.repositories.InventoryDetailRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.InventoryRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.ProductRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.UserInfoRepository;
import com.smartstore.smartstorewebservice.microservices.inventory.validation.InventoryValidator;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class InventoryService {
    private InventoryRepository inventoryRepository;
    private InventoryDetailRepository inventoryDetailRepository;
    private ProductRepository productRepository;
    private UserInfoRepository userInfoRepository;

    public InventoryService(InventoryRepository inventoryRepository,
                            InventoryDetailRepository inventoryDetailRepository,
                            ProductRepository productRepository,
                            UserInfoRepository userInfoRepository){
        this.inventoryRepository = inventoryRepository;
        this.inventoryDetailRepository = inventoryDetailRepository;
        this.productRepository = productRepository;
        this.userInfoRepository = userInfoRepository;
    }

    public HTTPAnswer addInventory(InventoryData inventory){
        List<String> errors = new InventoryValidator(inventory, productRepository, userInfoRepository).validate();
        if (errors.size() == 0) saveInventoryAndDetails(inventory);
        return HTTPAnswer.create(errors);
    }

    private void saveInventoryAndDetails(InventoryData inventory) {
        inventory.getInventory().setAddDate(new Date());
        var inv = this.inventoryRepository.save(inventory.getInventory());
        inventory.getDetails().forEach(detail ->{
            detail.setInventory(inv);
            this.inventoryDetailRepository.save(detail);
        });
    }

}
