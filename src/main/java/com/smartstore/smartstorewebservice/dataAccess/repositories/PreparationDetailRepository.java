package com.smartstore.smartstorewebservice.dataAccess.repositories;

import com.smartstore.smartstorewebservice.dataAccess.entities.Preparation;
import com.smartstore.smartstorewebservice.dataAccess.entities.PreparationDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PreparationDetailRepository extends JpaRepository<PreparationDetail, Long> {
    List<PreparationDetail> findAllByPreparation(Preparation preparation);
}