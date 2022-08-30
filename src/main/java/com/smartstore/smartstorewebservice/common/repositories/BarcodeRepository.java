package com.smartstore.smartstorewebservice.common.repositories;

import com.smartstore.smartstorewebservice.common.entities.Barcode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarcodeRepository extends JpaRepository<Barcode, String> {
}