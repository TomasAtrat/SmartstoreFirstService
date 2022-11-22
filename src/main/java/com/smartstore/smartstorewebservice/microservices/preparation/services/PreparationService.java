package com.smartstore.smartstorewebservice.microservices.preparation.services;

import com.smartstore.smartstorewebservice.common.wrappers.PreparationWrapper;
import com.smartstore.smartstorewebservice.dataAccess.entities.OrderDetail;
import com.smartstore.smartstorewebservice.dataAccess.entities.OrderInfo;
import com.smartstore.smartstorewebservice.dataAccess.entities.Preparation;
import com.smartstore.smartstorewebservice.dataAccess.entities.PreparationDetail;
import com.smartstore.smartstorewebservice.dataAccess.repositories.PreparationDetailRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.PreparationRepository;
import com.smartstore.smartstorewebservice.microservices.orders.services.OrderService;
import com.smartstore.smartstorewebservice.microservices.preparation.services.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PreparationService {

    private PreparationRepository preparationRepository;
    private PreparationDetailRepository preparationDetailRepository;
    private OrderService orderService;
    private UserService userService;


    @Autowired
    public PreparationService(PreparationRepository preparationRepository,
                              PreparationDetailRepository preparationDetailRepository,
                              OrderService orderService,
                              UserService userService) {
        this.preparationRepository = preparationRepository;
        this.preparationDetailRepository = preparationDetailRepository;
        this.orderService = orderService;
        this.userService = userService;
    }

    public PreparationWrapper getPreparation(Long orderId, Long user_id) {
        Preparation preparation = null;
        List<PreparationDetail> details = null;

        Optional<OrderInfo> order = orderService.getOrderById(orderId);

        if (order.isPresent()) {
            List<Preparation> preparations = preparationRepository.findAllByOrder(order.get());

            if (preparations == null || preparations.size() == 0) {
                preparation = createPreparation(order.get(), user_id);
                details = createPreparationDetails(preparation, order.get());
            } else if (preparations.stream().allMatch(Preparation::getIsFinished) && order.get().getAcceptsPartialExpedition()) {
                Preparation previousPreparation = preparations.stream().filter(Preparation::getIsFinished).findFirst().get();
                preparation = createPreparation(previousPreparation.getOrder(), user_id);
                details = createDetailsFromPreviousPreparation(preparation);
            } else if (preparations.stream().noneMatch(Preparation::getIsFinished)) {
                preparation = preparations.stream().filter(i -> !i.getIsFinished()).findFirst().orElse(null);
                details = createDetailsFromPreviousPreparation(preparation);
            }

            if (preparation != null) {
                savePreparationAndDetails(preparation, details);
                removeSensibleInformation(preparation, details);
            }
        }

        return new PreparationWrapper(preparation, details);
    }

    private void removeSensibleInformation(Preparation preparation, List<PreparationDetail> details) {
        preparation.getUserAssigned().setPasswordHash("");
        details.forEach(i -> i.setPreparation(preparation));
    }

    private List<PreparationDetail> createDetailsFromPreviousPreparation(Preparation preparation) {
        List<PreparationDetail> previousDetails = this.preparationDetailRepository.findAllByPreparation(preparation);
        previousDetails.stream().filter(f -> f.getOrderedQty() - f.getPreparedQty() != 0).forEach(i -> {
            i.setPreparation(preparation);
            i.setOrderedQty(i.getOrderedQty() - i.getPreparedQty());
            i.setPreparedQty(0);
            i.setBarcode(i.getBarcode());
            i.setAddDate(new Date());
        });

        return previousDetails;
    }

    private void savePreparationAndDetails(Preparation preparation, List<PreparationDetail> details) {
        preparationRepository.save(preparation);
        preparationDetailRepository.saveAll(details);
    }

    private List<PreparationDetail> createPreparationDetails(Preparation preparation, OrderInfo order) {
        List<PreparationDetail> details = new ArrayList<>();
        List<OrderDetail> orderDetails = this.orderService.getOrderDetailsByOrder(order);
        orderDetails.forEach(i -> {
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

    private Preparation createPreparation(OrderInfo orderInfo, Long user_id) {
        Preparation preparation = new Preparation();
        preparation.setOrder(orderInfo);
        preparation.setHasProblems(false);
        preparation.setIsFinished(false);
        preparation.setUserAssigned(userService.getUserById(user_id).get());
        preparation.setAddDate(new Date());
        return preparation;
    }

    public void savePreparationWrapper(PreparationWrapper wrapper) {
        savePreparationAndDetails(wrapper.getPreparation(), wrapper.getDetails());
    }
}
