package com.smartstore.smartstorewebservice.microservices.reception.services;

import com.smartstore.smartstorewebservice.common.data.Reception;
import com.smartstore.smartstorewebservice.common.wrappers.HTTPAnswer;
import com.smartstore.smartstorewebservice.dataAccess.entities.ReceptionDetailRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.ProductRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.ReceptionListRepository;
import com.smartstore.smartstorewebservice.microservices.reception.validation.ReceptionValidator;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceptionService {
    private ReceptionListRepository receptionListRepository;
    private ReceptionDetailRepository receptionDetailRepository;
    private ProductRepository productRepository;

    public ReceptionService(ReceptionListRepository receptionListRepository,
                            ReceptionDetailRepository receptionDetailRepository,
                            ProductRepository productRepository){
        this.receptionListRepository = receptionListRepository;
        this.receptionDetailRepository = receptionDetailRepository;
        this.productRepository = productRepository;
    }

    public HTTPAnswer addReception(Reception reception) {
        List<String> errors = new ReceptionValidator(reception, productRepository).validate();
        if (errors.size() == 0) saveReceptionListAndDetails(reception);
        return HTTPAnswer.create(errors);
    }

    private void saveReceptionListAndDetails(Reception reception) {
        var list = this.receptionListRepository.save(reception.getReceptionList());
        reception.getDetails().forEach(detail -> {
            detail.setReceptionList(list);
            this.receptionDetailRepository.save(detail);
        });
    }

}
