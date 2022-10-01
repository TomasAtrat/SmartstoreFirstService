package com.smartstore.smartstorewebservice.dataAccess.repositories;

import com.smartstore.smartstorewebservice.dataAccess.entities.ReceptionDetail;
import com.smartstore.smartstorewebservice.dataAccess.entities.ReceptionList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReceptionDetailRepository extends JpaRepository<ReceptionDetail, Long> {
    List<ReceptionDetail> findAllByReceptionList(Optional<ReceptionList> receptionList);
}