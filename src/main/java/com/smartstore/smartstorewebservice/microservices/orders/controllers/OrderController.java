package com.smartstore.smartstorewebservice.microservices.orders.controllers;

import com.smartstore.smartstorewebservice.dataAccess.entities.OrderDetail;
import com.smartstore.smartstorewebservice.dataAccess.entities.OrderInfo;
import com.smartstore.smartstorewebservice.microservices.orders.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrderController {

    private OrderService orderService;

    @PostMapping("/")
    public void addOrder(@RequestBody final OrderInfo order) {

    }

    @GetMapping("/")
    public List<OrderInfo> getOrders() {
        return this.orderService.getOrders();
    }

    @PostMapping("/details/")
    public void addDetail(@RequestBody final OrderDetail order) {

    }

    @GetMapping("details/")
    public List<OrderDetail> getDetails() {
        return null;
    }

    @GetMapping("details/")
    public OrderDetail getDetail() {
        return null;
    }
}
