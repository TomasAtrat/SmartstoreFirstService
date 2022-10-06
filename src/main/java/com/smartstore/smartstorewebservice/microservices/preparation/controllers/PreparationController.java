package com.smartstore.smartstorewebservice.microservices.preparation.controllers;

import com.smartstore.smartstorewebservice.common.wrappers.ListOfOrderWrapper;
import com.smartstore.smartstorewebservice.dataAccess.entities.Preparation;
import com.smartstore.smartstorewebservice.microservices.preparation.services.PreparationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/preparation")
@AllArgsConstructor
public class PreparationController {

    private PreparationService preparationService;

    @GetMapping("/{order_id}")
    public Preparation getPreparation(@PathVariable Long order_id) {
        return this.preparationService.getPreparation(order_id);
    }
}
