package com.smartstore.smartstorewebservice.microservices.orders.controllers;

import com.smartstore.smartstorewebservice.common.wrappers.HTTPAnswer;
import com.smartstore.smartstorewebservice.common.wrappers.ListOfOrderWrapper;
import com.smartstore.smartstorewebservice.common.wrappers.OrderWrapper;
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
    public HTTPAnswer addOrder(@RequestBody final OrderWrapper order) {
        List<String> errors = orderService.isOrderValid(order);
        if (errors.size() == 0) {
            orderService.saveCustomer(order.getOrderInfo().getCustomer());
            orderService.saveDetails(order.getOrderDetailList());
        }

        return HTTPAnswer.create(errors);
    }

    @GetMapping("/")
    public ListOfOrderWrapper getOrders() {
        return this.orderService.getOrders();
    }

    @PostMapping("/details/")
    public OrderDetail addDetail(@RequestBody final OrderDetail detail) {
        return this.orderService.addDetail(detail);
    }

    @GetMapping("details/")
    public List<OrderDetail> getDetails() {
        return null;
    }

    @GetMapping("/to-prepare")
    public ListOfOrderWrapper getOrdersToPrepare() {
        return new ListOfOrderWrapper(this.orderService.getOrdersToPrepare());
    }
}
