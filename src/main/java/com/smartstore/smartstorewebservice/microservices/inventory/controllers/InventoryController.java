package com.smartstore.smartstorewebservice.microservices.inventory.controllers;

import com.smartstore.smartstorewebservice.common.data.InventoryData;
import com.smartstore.smartstorewebservice.common.wrappers.HTTPAnswer;
import com.smartstore.smartstorewebservice.microservices.inventory.services.InventoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/inventories")
@AllArgsConstructor
public class InventoryController {

    private InventoryService inventoryService;

    @PostMapping("/")
    public HTTPAnswer addInventory(@RequestBody final InventoryData inventory) {
        return this.inventoryService.addInventory(inventory);
    }
}
