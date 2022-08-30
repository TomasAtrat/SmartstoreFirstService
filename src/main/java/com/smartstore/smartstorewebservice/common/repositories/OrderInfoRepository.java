package com.smartstore.smartstorewebservice.common.repositories;

import com.smartstore.smartstorewebservice.common.entities.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInfoRepository extends JpaRepository<OrderInfo, Long> {
}