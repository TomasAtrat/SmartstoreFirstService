package com.smartstore.smartstorewebservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class SmartStoreWebServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartStoreWebServiceApplication.class, args);
    }

}
