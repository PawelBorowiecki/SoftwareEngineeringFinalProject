package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Client {
    private String id;
    private List<Order> placedOrders;

    public Client(){
        this.id = UUID.randomUUID().toString();
        this.placedOrders = new ArrayList<>();
    }

    public String getId(){
        return this.id;
    }

    public List<Order> getPlacedOrders(){
        return this.placedOrders;
    }

    public void addPlacedOrder(Order order){
        this.placedOrders.add(order);
    }
}
