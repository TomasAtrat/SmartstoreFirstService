package com.smartstore.smartstorewebservice.microservices.inventory.controllers;

import com.smartstore.smartstorewebservice.common.data.InventoryData;
import com.smartstore.smartstorewebservice.common.wrappers.HTTPAnswer;
import com.smartstore.smartstorewebservice.common.wrappers.ListOfInventories;
import com.smartstore.smartstorewebservice.microservices.inventory.services.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/inventories")
@AllArgsConstructor
public class InventoryController {

    private InventoryService inventoryService;

    @PostMapping("/")
    public HTTPAnswer addInventory(@RequestBody final InventoryData inventory) {
        return this.inventoryService.addInventory(inventory);
    }

    @GetMapping("/")
    public ListOfInventories getAvailableInventories() {
        return new ListOfInventories(this.inventoryService.getAvailableInventories());
    }

    @GetMapping("/details/{id}")
    public InventoryData getInventoryDetails(@PathVariable Long id) {
        return new InventoryData(null, this.inventoryService.getInventoryDetails(id), null);
    }
}
