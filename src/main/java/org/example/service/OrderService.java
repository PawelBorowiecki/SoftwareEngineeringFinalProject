package org.example.service;

import org.example.model.Log;
import org.example.model.Order;
import org.example.repository.OrderRepository;

import java.util.Optional;

public class OrderService {
    private final OrderRepository orderRepository;
    private final LogRegister logRegister;

    public OrderService(OrderRepository orderRepository, LogRegister logRegister) {
        this.orderRepository = orderRepository;
        this.logRegister = logRegister;
    }

    public void placeOrder(Order order){
        this.orderRepository.addOrder(order);
        log(order, order.getEmployeeId(), "Active");
    }

    public boolean cancellOrder(String orderId){
        Optional<Order> order = this.orderRepository.getOrder(orderId);
        if(order.isPresent()){
            order.get().cancell();
            this.orderRepository.deleteById(orderId);
            this.orderRepository.addOrder(order.get());
            log(order.get(), order.get().getEmployeeId(), "Cancelled");
            return true;
        }else{
            System.out.println("Zamowienie nie istnieje.");
            return false;
        }
    }

    private void log(Order order, String employeeId, String state){
        Log log = new Log(order.getId(), employeeId,state);
        this.logRegister.addLog(log);
    }
}
