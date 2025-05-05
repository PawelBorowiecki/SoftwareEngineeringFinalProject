package org.example.repository;

import org.example.model.Order;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderRepository {
    private List<Order> orders;

    public OrderRepository(){
        this.orders = new ArrayList<>();
    }

    public List<Order> getOrders(){
        return this.orders;
    }

    public Optional<Order> getOrder(int id){
        return this.orders.stream().filter(o -> o.getId() == id).findFirst();
    }

    public List<Order> getOrdersByDate(String date){
        return this.orders.stream().filter(o -> o.getDate().equals(date)).toList();
    }

    public List<Order> getOrdersByClientId(int clientId){
        return this.orders.stream().filter(o -> o.getClientId() == clientId).toList();
    }

    public void addOrder(Order order){
        this.orders.add(order);
    }
}
