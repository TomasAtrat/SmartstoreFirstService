package com.smartstore.smartstorewebservice.dataAccess.repositories;

import com.smartstore.smartstorewebservice.dataAccess.entities.ExpeditionType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpeditionTypeRepository extends JpaRepository<ExpeditionType, Long> {
}