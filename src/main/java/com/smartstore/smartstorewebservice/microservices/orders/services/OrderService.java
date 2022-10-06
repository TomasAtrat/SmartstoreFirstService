package com.smartstore.smartstorewebservice.microservices.orders.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.smartstore.smartstorewebservice.common.wrappers.ListOfOrderWrapper;
import com.smartstore.smartstorewebservice.common.wrappers.OrderWrapper;
import com.smartstore.smartstorewebservice.dataAccess.entities.*;
import com.smartstore.smartstorewebservice.dataAccess.repositories.*;
import com.smartstore.smartstorewebservice.microservices.orders.validation.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@Service
public class OrderService {

    private final OrderInfoRepository orderInfoRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CustomerRepository customerRepository;
    private StockRepository stockRepository;
    private VOrdersToPrepareRepository vOrdersToPrepareRepository;

    @Autowired
    public OrderService(OrderInfoRepository orderInfoRepository,
                        OrderDetailRepository orderDetailRepository,
                        CustomerRepository customerRepository,
                        StockRepository stockRepository,
                        VOrdersToPrepareRepository vOrdersToPrepareRepository) {
        this.orderInfoRepository = orderInfoRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.customerRepository = customerRepository;
        this.stockRepository = stockRepository;
        this.vOrdersToPrepareRepository = vOrdersToPrepareRepository;
    }

    public List<String> isOrderValid(OrderWrapper wrapper){
        return new OrderValidator(wrapper.getOrderInfo()).validate();
    }

    @Transactional(isolation = READ_UNCOMMITTED)
    public void saveDetails(List<OrderDetail> orderDetailList) {
        orderDetailRepository.saveAll(orderDetailList);
        orderDetailList.forEach(i-> updateStockReservedQty(i));
    }

    private void updateStockReservedQty(OrderDetail detail) {
        Stock stock = this.stockRepository.findByBarcodeBarcodeAndBranch(detail.getBarcode(), detail.getOrderInfo().getBranch());
        stock.setQtReserve(stock.getQtReserve() + detail.getOrderedQuantity());
        this.stockRepository.save(stock);
    }

    @Transactional(isolation = READ_UNCOMMITTED)
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Transactional(isolation = READ_UNCOMMITTED)
    public void saveOrder(OrderInfo orderInfo) {
        orderInfoRepository.save(orderInfo);
    }

    public ListOfOrderWrapper getOrders() {
        return new ListOfOrderWrapper(this.orderInfoRepository.findAll());
    }

    public OrderDetail addDetail(OrderDetail orderDetail) {
        if (orderInfoRepository.existsById(orderDetail.getOrderInfo().getId()))
            return orderDetailRepository.save(orderDetail);
        return null;
    }

    public List<OrderInfo> getOrdersToPrepare(){
        var ordersToPrepare = vOrdersToPrepareRepository.findAll();
        List<OrderInfo> orders = new ArrayList<>();

        ordersToPrepare.forEach(i-> {
            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss.SSSZ").create();

            OrderInfo order = gson.fromJson(gson.toJson(i), OrderInfo.class);

            setAttachedObjectsToOrder(order, i);

            orders.add(order);
        });

        return orders;
    }

    private void setAttachedObjectsToOrder(OrderInfo order, VOrdersToPrepare ordersToPrepare) {
        Branch branch = new Branch();
        branch.setId(ordersToPrepare.getBranchId());
        order.setBranch(branch);

        ExpeditionType expeditionType = new ExpeditionType();
        expeditionType.setId(ordersToPrepare.getExpeditionId());
        order.setExpedition(expeditionType);

        Customer customer = new Customer();
        customer.setId(ordersToPrepare.getCustomerId());
        order.setCustomer(customer);
    }

    public Optional<OrderInfo> getOrderById(Long id){
        return orderInfoRepository.findById(id);
    }

    public List<OrderDetail> getOrderDetailsByOrder(OrderInfo order) {
        return this.orderDetailRepository.findAllByOrderInfo(order);
    }
}
