package com.smartstore.smartstorewebservice.common.repositories;

import com.smartstore.smartstorewebservice.common.entities.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}