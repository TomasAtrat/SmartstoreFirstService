package com.smartstore.smartstorewebservice.dataAccess.repositories;

import com.smartstore.smartstorewebservice.dataAccess.entities.ReceptionList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceptionListRepository extends JpaRepository<ReceptionList, Long> {
    List<ReceptionList> findAllByEndingDateIsNull();
}