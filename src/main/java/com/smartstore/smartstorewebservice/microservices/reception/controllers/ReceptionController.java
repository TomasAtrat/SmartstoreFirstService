package com.smartstore.smartstorewebservice.microservices.reception.controllers;

import com.smartstore.smartstorewebservice.common.data.Reception;
import com.smartstore.smartstorewebservice.common.wrappers.HTTPAnswer;
import com.smartstore.smartstorewebservice.microservices.reception.services.ReceptionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/reception")
@AllArgsConstructor
public class ReceptionController {

    private ReceptionService receptionService;

    @PostMapping("/")
    public HTTPAnswer addReception(@RequestBody final Reception reception) {
        return receptionService.addReception(reception);
    }

}

