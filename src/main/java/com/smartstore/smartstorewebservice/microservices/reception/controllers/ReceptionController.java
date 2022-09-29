package com.smartstore.smartstorewebservice.microservices.reception.controllers;

import com.smartstore.smartstorewebservice.common.data.Reception;
import com.smartstore.smartstorewebservice.common.wrappers.HTTPAnswer;
import com.smartstore.smartstorewebservice.common.wrappers.ListOfInventories;
import com.smartstore.smartstorewebservice.common.wrappers.ListOfReception;
import com.smartstore.smartstorewebservice.microservices.reception.services.ReceptionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/reception")
@AllArgsConstructor
public class ReceptionController {

    private ReceptionService receptionService;

    @PostMapping("/")
    public HTTPAnswer addReception(@RequestBody final Reception reception) {
        return receptionService.addReception(reception);
    }

    @GetMapping("/")
    public ListOfReception getAvailableReferences() {
        return new ListOfReception(this.receptionService.getAvailableReferences());
    }

}

