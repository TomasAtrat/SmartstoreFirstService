package com.smartstore.smartstorewebservice.dataAccess.repositories;

import com.smartstore.smartstorewebservice.dataAccess.entities.InventoryProblem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryProblemRepository extends JpaRepository<InventoryProblem, Long> {
}