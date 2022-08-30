package com.smartstore.smartstorewebservice.orders.services;

import com.smartstore.smartstorewebservice.common.entities.OrderDetail;
import com.smartstore.smartstorewebservice.common.entities.OrderInfo;
import com.smartstore.smartstorewebservice.common.repositories.OrderDetailRepository;
import com.smartstore.smartstorewebservice.common.repositories.OrderInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    }

    public void addDetail(OrderDetail orderDetail){

    }

}
