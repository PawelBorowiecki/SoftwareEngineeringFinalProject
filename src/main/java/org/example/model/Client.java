package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Client {
    private final String id;
    private final List<Order> placedOrders;

    public Client(){
        this.id = UUID.randomUUID().toString();
        this.placedOrders = new ArrayList<>();
    }

    public String getId(){
        return this.id;
    }

    public void addPlacedOrder(Order order){
        this.placedOrders.add(order);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", placedOrders=" + placedOrders +
                '}';
    }
}
