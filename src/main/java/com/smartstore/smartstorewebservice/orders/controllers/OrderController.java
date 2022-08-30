package com.smartstore.smartstorewebservice.orders.controllers;

import com.smartstore.smartstorewebservice.common.entities.OrderDetail;
import com.smartstore.smartstorewebservice.common.entities.OrderInfo;
import com.smartstore.smartstorewebservice.orders.services.OrderService;
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

    @PostMapping("/details/")
    public void addOrder(@RequestBody final OrderDetail order) {

    }

    @GetMapping("d")
    public List<OrderDetail> getDetails() {
        return null;
    }

    @GetMapping("")
    public OrderDetail getDetail() {
        return null;
    }
}
