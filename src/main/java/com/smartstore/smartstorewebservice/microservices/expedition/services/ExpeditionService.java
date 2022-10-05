package com.smartstore.smartstorewebservice.microservices.expedition.services;

import com.smartstore.smartstorewebservice.dataAccess.entities.ExpeditionType;
import com.smartstore.smartstorewebservice.dataAccess.repositories.ExpeditionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpeditionService {
    private ExpeditionTypeRepository expeditionTypeRepository;

    @Autowired
    public ExpeditionService(ExpeditionTypeRepository expeditionTypeRepository) {
        this.expeditionTypeRepository = expeditionTypeRepository;
    }

    public List<ExpeditionType> findAllExpeditionTypes(){
        return this.expeditionTypeRepository.findAll();
    }
}
