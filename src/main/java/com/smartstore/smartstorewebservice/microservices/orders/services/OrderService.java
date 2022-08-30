package com.smartstore.smartstorewebservice.microservices.orders.services;

import com.smartstore.smartstorewebservice.dataAccess.entities.OrderDetail;
import com.smartstore.smartstorewebservice.dataAccess.entities.OrderInfo;
import com.smartstore.smartstorewebservice.dataAccess.repositories.OrderDetailRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.OrderInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private OrderInfoRepository orderInfoRepository;
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderService(OrderInfoRepository orderInfoRepository,
                        OrderDetailRepository orderDetailRepository){

        this.orderInfoRepository = orderInfoRepository;
        this.orderDetailRepository = orderDetailRepository;
    }

    public void addOrder(OrderInfo orderInfo){
        this.addOrder(orderInfo);
    }

    public void addDetail(OrderDetail orderDetail){
        this.addDetail(orderDetail);
    }

    public List<OrderInfo> getOrders() {
        return this.orderInfoRepository.findAll();
    }
}
