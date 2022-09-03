package com.smartstore.smartstorewebservice.dataAccess.repositories;

import com.smartstore.smartstorewebservice.dataAccess.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}