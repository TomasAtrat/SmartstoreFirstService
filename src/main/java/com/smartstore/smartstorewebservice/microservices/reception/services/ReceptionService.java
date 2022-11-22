package com.smartstore.smartstorewebservice.microservices.reception.services;

import com.smartstore.smartstorewebservice.common.data.Reception;
import com.smartstore.smartstorewebservice.common.wrappers.HTTPAnswer;
import com.smartstore.smartstorewebservice.dataAccess.entities.ReceptionDetail;
import com.smartstore.smartstorewebservice.dataAccess.entities.ReceptionList;
import com.smartstore.smartstorewebservice.dataAccess.entities.ReceptionProblem;
import com.smartstore.smartstorewebservice.dataAccess.repositories.BarcodeRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.ReceptionDetailRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.ReceptionListRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.ReceptionProblemRepository;
import com.smartstore.smartstorewebservice.microservices.reception.validation.ReceptionValidator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceptionService {
    private ReceptionListRepository receptionListRepository;
    private ReceptionDetailRepository receptionDetailRepository;
    private ReceptionProblemRepository receptionProblemRepository;
    private BarcodeRepository barcodeRepository;

    public ReceptionService(ReceptionListRepository receptionListRepository,
                            ReceptionDetailRepository receptionDetailRepository,
                            ReceptionProblemRepository receptionProblemRepository,
                            BarcodeRepository barcodeRepository){
        this.receptionListRepository = receptionListRepository;
        this.receptionDetailRepository = receptionDetailRepository;
        this.receptionProblemRepository = receptionProblemRepository;
        this.barcodeRepository = barcodeRepository;
    }

    public HTTPAnswer addReception(Reception reception) {
        List<String> errors = new ReceptionValidator(reception, barcodeRepository).validate();
        if (errors.size() == 0) saveReceptionListAndDetails(reception);
        return HTTPAnswer.create(errors);
    }

    private void saveReceptionListAndDetails(Reception reception) {
        ReceptionList list = this.receptionListRepository.save(reception.getReceptionList());
        reception.getDetails().forEach(detail -> {
            detail.setReceptionList(list);
            this.receptionDetailRepository.save(detail);
        });

        List<ReceptionProblem> problems = reception.getProblems();
        if (problems != null && problems.size() > 0)
            receptionProblemRepository.saveAll(problems);
    }

    public List<ReceptionList> getAvailableReferences() {
        List<ReceptionList> references = this.receptionListRepository.findAllByEndingDateIsNull();
        return references;
    }

    public List<ReceptionDetail> getReceptionDetails(Long id) {
        Optional<ReceptionList> reception = this.receptionListRepository.findById(id);
        if (reception.isPresent()) {
            List<ReceptionDetail> details = this.receptionDetailRepository.findAllByReceptionList(reception);
            details.forEach(i -> i.setReceptionList(null));
            return details;
        }

        return null;
    }
}
