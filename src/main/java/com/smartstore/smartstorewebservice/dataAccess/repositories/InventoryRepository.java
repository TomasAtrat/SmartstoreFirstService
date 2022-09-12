package com.smartstore.smartstorewebservice.dataAccess.repositories;

import com.smartstore.smartstorewebservice.dataAccess.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}