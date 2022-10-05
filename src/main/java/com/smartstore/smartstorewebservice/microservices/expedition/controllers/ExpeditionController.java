package com.smartstore.smartstorewebservice.microservices.expedition.controllers;

import com.smartstore.smartstorewebservice.common.wrappers.ListOfExpeditionType;
import com.smartstore.smartstorewebservice.microservices.expedition.services.ExpeditionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/expedition")
@AllArgsConstructor
public class ExpeditionController {
    private ExpeditionService expeditionService;

    @GetMapping("/types/")
    public ListOfExpeditionType getExpeditionTypes() {
        return new ListOfExpeditionType(this.expeditionService.findAllExpeditionTypes());
    }
}
