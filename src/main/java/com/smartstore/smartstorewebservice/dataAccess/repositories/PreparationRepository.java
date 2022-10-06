package com.smartstore.smartstorewebservice.dataAccess.repositories;

import com.smartstore.smartstorewebservice.dataAccess.entities.OrderInfo;
import com.smartstore.smartstorewebservice.dataAccess.entities.Preparation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreparationRepository extends JpaRepository<Preparation, Long> {
    List<Preparation> findAllByOrder(OrderInfo order);
}