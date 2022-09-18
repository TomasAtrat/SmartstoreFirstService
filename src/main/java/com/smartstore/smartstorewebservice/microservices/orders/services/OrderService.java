package com.smartstore.smartstorewebservice.microservices.orders.services;

import com.smartstore.smartstorewebservice.common.wrappers.HTTPAnswer;
import com.smartstore.smartstorewebservice.common.wrappers.ListOfOrderWrapper;
import com.smartstore.smartstorewebservice.dataAccess.entities.OrderDetail;
import com.smartstore.smartstorewebservice.dataAccess.entities.OrderInfo;
import com.smartstore.smartstorewebservice.dataAccess.repositories.CustomerRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.OrderDetailRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.OrderInfoRepository;
import com.smartstore.smartstorewebservice.microservices.orders.validation.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderInfoRepository orderInfoRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(OrderInfoRepository orderInfoRepository,
                        OrderDetailRepository orderDetailRepository,
                        CustomerRepository customerRepository) {
        this.orderInfoRepository = orderInfoRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.customerRepository = customerRepository;
    }

    public HTTPAnswer addOrder(OrderInfo orderInfo) {
        List<String> errors = new OrderValidator(orderInfo).validate();
        if (errors.size() == 0) saveOrderAndAttachedEntities(orderInfo);
        return createHTTPAnswer(errors);
    }

    private HTTPAnswer createHTTPAnswer(List<String> errors) {
        if(errors.size() > 0)
            return new HTTPAnswer(400, errors);
        return new HTTPAnswer(200, errors);
    }

    private void saveOrderAndAttachedEntities(OrderInfo orderInfo) {
        customerRepository.save(orderInfo.getCustomer());
        this.orderInfoRepository.save(orderInfo);
    }

    public ListOfOrderWrapper getOrders() {
        return new ListOfOrderWrapper(this.orderInfoRepository.findAll());
    }

    public OrderDetail addDetail(OrderDetail orderDetail) {
        if(orderInfoRepository.existsById(orderDetail.getOrderInfo().getId()))
            return orderDetailRepository.save(orderDetail);
        return null;
    }

}
