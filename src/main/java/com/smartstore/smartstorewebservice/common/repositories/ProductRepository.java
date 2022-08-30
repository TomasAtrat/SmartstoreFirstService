package com.smartstore.smartstorewebservice.common.repositories;

import com.smartstore.smartstorewebservice.common.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {

}
