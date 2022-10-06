package com.smartstore.smartstorewebservice.microservices.preparation.services;

import com.smartstore.smartstorewebservice.dataAccess.entities.OrderDetail;
import com.smartstore.smartstorewebservice.dataAccess.entities.OrderInfo;
import com.smartstore.smartstorewebservice.dataAccess.entities.Preparation;
import com.smartstore.smartstorewebservice.dataAccess.entities.PreparationDetail;
import com.smartstore.smartstorewebservice.dataAccess.repositories.PreparationDetailRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.PreparationRepository;
import com.smartstore.smartstorewebservice.microservices.orders.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PreparationService {

    private PreparationRepository preparationRepository;
    private PreparationDetailRepository preparationDetailRepository;
    private OrderService orderService;


    @Autowired
    public PreparationService(PreparationRepository preparationRepository,
                              PreparationDetailRepository preparationDetailRepository,
                              OrderService orderService) {
        this.preparationRepository = preparationRepository;
        this.preparationDetailRepository = preparationDetailRepository;

        this.orderService = orderService;
    }

    public Preparation getPreparation(Long orderId){
        Preparation preparation = null;
        List<PreparationDetail> details;

        var order = orderService.getOrderById(orderId);

        if(order.isPresent()){
            List<Preparation> preparations = preparationRepository.findAllByOrder(order.get());

            if(preparations == null || preparations.size() == 0){
                preparation = createPreparation(order.get());
                details = createPreparationDetails(preparation, order.get());
                savePreparationAndDetails(preparation, details);
            }
        }

        return preparation;
    }

    private void savePreparationAndDetails(Preparation preparation, List<PreparationDetail> details) {
        preparationRepository.save(preparation);
        preparationDetailRepository.saveAll(details);
    }

    private List<PreparationDetail> createPreparationDetails(Preparation preparation, OrderInfo order) {
        List<PreparationDetail> details = new ArrayList<>();
        var orderDetails = this.orderService.getOrderDetailsByOrder(order);
        orderDetails.forEach(i->{
            details.add(createPreparationDetail(preparation, i));
        });
        return details;
    }

    private PreparationDetail createPreparationDetail(Preparation preparation, OrderDetail detail) {
        PreparationDetail det = new PreparationDetail();
        det.setPreparation(preparation);
        det.setBarcode(detail.getBarcode());
        det.setOrderedQty(detail.getOrderedQuantity());
        det.setPreparedQty(0);
        det.setAddDate(new Date());
        return det;
    }

    private Preparation createPreparation(OrderInfo orderInfo) {
        Preparation preparation = new Preparation();
        preparation.setOrder(orderInfo);
        preparation.setHasProblems(false);
        preparation.setIsFinished(false);
        preparation.setAddDate(new Date());
        return preparation;
    }
}
