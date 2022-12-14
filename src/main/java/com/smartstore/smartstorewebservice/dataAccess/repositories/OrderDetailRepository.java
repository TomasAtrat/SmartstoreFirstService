package com.smartstore.smartstorewebservice.dataAccess.repositories;

import com.smartstore.smartstorewebservice.dataAccess.entities.OrderDetail;
import com.smartstore.smartstorewebservice.dataAccess.entities.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
    List<OrderDetail> findAllByOrderInfo(OrderInfo order);
}