package org.example.service;

import org.example.model.Log;
import org.example.model.Order;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;

import java.util.Optional;

public class OrderService {
    private ProductRepository productRepository;
    private OrderRepository orderRepository;
    private LogRegister logRegister;

    public OrderService(ProductRepository productRepository, OrderRepository orderRepository, LogRegister logRegister) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.logRegister = logRegister;
    }

    public void placeOrder(Order order){
        this.orderRepository.addOrder(order);
        log(order, order.getEmployeeId(), "Active");
    }

    public void cancellOrder(int orderId){
        Optional<Order> order = this.orderRepository.getOrder(orderId);
        if(order.isPresent()){
            order.get().cancell();
            log(order.get(), order.get().getEmployeeId(), "Cancelled");
        }else{
            System.out.println("Zamowienie nie istnieje.");
        }
    }

    private void log(Order order, int employeeId, String state){
        Log log = new Log(order.getId(), employeeId,state);
        this.logRegister.addLog(log);
    }
}
