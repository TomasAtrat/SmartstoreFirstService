package com.smartstore.smartstorewebservice.microservices.orders.services;

import com.smartstore.smartstorewebservice.common.wrappers.HTTPAnswer;
import com.smartstore.smartstorewebservice.common.wrappers.ListOfOrderWrapper;
import com.smartstore.smartstorewebservice.common.wrappers.OrderWrapper;
import com.smartstore.smartstorewebservice.dataAccess.entities.Customer;
import com.smartstore.smartstorewebservice.dataAccess.entities.OrderDetail;
import com.smartstore.smartstorewebservice.dataAccess.entities.OrderInfo;
import com.smartstore.smartstorewebservice.dataAccess.entities.Stock;
import com.smartstore.smartstorewebservice.dataAccess.repositories.CustomerRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.OrderDetailRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.OrderInfoRepository;
import com.smartstore.smartstorewebservice.dataAccess.repositories.StockRepository;
import com.smartstore.smartstorewebservice.microservices.orders.validation.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.springframework.transaction.annotation.Isolation.READ_UNCOMMITTED;

@Service
public class OrderService {

    private final OrderInfoRepository orderInfoRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final CustomerRepository customerRepository;
    private StockRepository stockRepository;

    @Autowired
    public OrderService(OrderInfoRepository orderInfoRepository,
                        OrderDetailRepository orderDetailRepository,
                        CustomerRepository customerRepository,
                        StockRepository stockRepository) {
        this.orderInfoRepository = orderInfoRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.customerRepository = customerRepository;
        this.stockRepository = stockRepository;
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

}
