package com.smartstore.smartstorewebservice.dataAccess.repositories;

import com.smartstore.smartstorewebservice.dataAccess.entities.InventoryDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryDetailRepository extends JpaRepository<InventoryDetail, Long> {
}