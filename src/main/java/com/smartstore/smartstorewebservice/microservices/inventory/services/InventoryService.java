package com.smartstore.smartstorewebservice.microservices.inventory.services;

import com.smartstore.smartstorewebservice.common.data.InventoryData;
import com.smartstore.smartstorewebservice.common.wrappers.HTTPAnswer;
import com.smartstore.smartstorewebservice.dataAccess.entities.*;
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
    private StockRepository stockRepository;

    public InventoryService(InventoryRepository inventoryRepository,
                            InventoryDetailRepository inventoryDetailRepository,
                            InventoryProblemRepository inventoryProblemRepository,
                            BarcodeRepository barcodeRepository,
                            UserInfoRepository userInfoRepository,
                            StockRepository stockRepository) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryDetailRepository = inventoryDetailRepository;
        this.inventoryProblemRepository = inventoryProblemRepository;
        this.barcodeRepository = barcodeRepository;
        this.userInfoRepository = userInfoRepository;
        this.stockRepository = stockRepository;
    }

    public HTTPAnswer addInventory(InventoryData inventory) {
        List<String> errors = new InventoryValidator(inventory, barcodeRepository, userInfoRepository).validate();
        if (errors.size() == 0) saveInventoryAndAttachedObjects(inventory);
        return HTTPAnswer.create(errors);
    }

    private void saveInventoryAndAttachedObjects(InventoryData inventory) {
        if (inventory.getInventory().getAddDate() == null)
            inventory.getInventory().setAddDate(new Date());

        Inventory inv = this.inventoryRepository.save(inventory.getInventory());

        inventory.getDetails().forEach(detail -> {
            detail.setInventory(inv);
            this.inventoryDetailRepository.save(detail);
        });

        List<InventoryProblem> problems = inventory.getProblems();

        if (problems != null && problems.size() > 0)
            inventoryProblemRepository.saveAll(problems);
        else updateStock(inventory.getDetails());
    }

    private void updateStock(List<InventoryDetail> details) {
        details.forEach(detail-> {
            Branch branch = new Branch();
            branch.setId(detail.getInventory().getUserAssigned().getIdBranch());
            Stock stock = stockRepository.findByBarcodeBarcodeAndBranch(detail.getBarcode(), branch);

            stock.setQtStock(stock.getQtStock() + detail.getReadQty());

            stockRepository.save(stock);
        });
    }

    public List<Inventory> getAvailableInventories() {
        List<Inventory> inventories = this.inventoryRepository.findAllByActiveIsTrue();
        inventories.forEach(i -> i.getUserAssigned().setPasswordHash(""));
        return inventories;
    }

    public List<InventoryDetail> getInventoryDetails(Long id) {
        Optional<Inventory> inventory = this.inventoryRepository.findById(id);
        if (inventory.isPresent()) {
            List<InventoryDetail> details = this.inventoryDetailRepository.findAllByInventory(inventory);
            details.forEach(i -> i.setInventory(null));
            return details;
        }
        return null;
    }
}
