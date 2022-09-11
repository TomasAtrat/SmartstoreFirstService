package com.smartstore.smartstorewebservice.dataAccess.repositories;

import com.smartstore.smartstorewebservice.dataAccess.entities.VTasksByUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VTasksByUserRepository extends JpaRepository<VTasksByUser, Long> {
}