package com.smartstore.smartstorewebservice.microservices.inventory.services;

import com.smartstore.smartstorewebservice.common.data.InventoryData;
import com.smartstore.smartstorewebservice.common.wrappers.HTTPAnswer;
import com.smartstore.smartstorewebservice.dataAccess.entities.Inventory;
import com.smartstore.smartstorewebservice.dataAccess.entities.InventoryDetail;
import com.smartstore.smartstorewebservice.dataAccess.repositories.*;
import com.smartstore.smartstorewebservice.microservices.inventory.validation.InventoryValidator;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {
    private InventoryRepository inventoryRepository;
    private InventoryDetailRepository inventoryDetailRepository;
    private InventoryProblemRepository inventoryProblemRepository;
    private BarcodeRepository barcodeRepository;
    private UserInfoRepository userInfoRepository;

    public InventoryService(InventoryRepository inventoryRepository,
                            InventoryDetailRepository inventoryDetailRepository,
                            InventoryProblemRepository inventoryProblemRepository,
                            BarcodeRepository barcodeRepository,
                            UserInfoRepository userInfoRepository) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryDetailRepository = inventoryDetailRepository;
        this.inventoryProblemRepository = inventoryProblemRepository;
        this.barcodeRepository = barcodeRepository;
        this.userInfoRepository = userInfoRepository;
    }

    public HTTPAnswer addInventory(InventoryData inventory) {
        List<String> errors = new InventoryValidator(inventory, barcodeRepository, userInfoRepository).validate();
        if (errors.size() == 0) saveInventoryAndAttachedObjects(inventory);
        return HTTPAnswer.create(errors);
    }

    private void saveInventoryAndAttachedObjects(InventoryData inventory) {
        if (inventory.getInventory().getAddDate() == null)
            inventory.getInventory().setAddDate(new Date());

        var inv = this.inventoryRepository.save(inventory.getInventory());

        inventory.getDetails().forEach(detail -> {
            detail.setInventory(inv);
            this.inventoryDetailRepository.save(detail);
        });

        var problems = inventory.getProblems();
        if (problems != null && problems.size() > 0)
            inventoryProblemRepository.saveAll(problems);
    }

    public List<Inventory> getAvailableInventories() {
        var inventories = this.inventoryRepository.findAllByActiveIsTrue();
        inventories.forEach(i -> i.getUserAssigned().setPasswordHash(""));
        return inventories;
    }

    public List<InventoryDetail> getInventoryDetails(Long id) {
        Optional<Inventory> inventory = this.inventoryRepository.findById(id);
        if (inventory.isPresent()) {
            var details = this.inventoryDetailRepository.findAllByInventory(inventory);
            details.forEach(i -> i.setInventory(null));
            return details;
        }
        return null;
    }
}
