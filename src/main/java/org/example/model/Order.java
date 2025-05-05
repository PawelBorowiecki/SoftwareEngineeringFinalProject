package org.example.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Order {
    private int id;
    private LocalDateTime date;
    private List<Product> products;
    private int clientId;
    private int employeeId;
    private boolean isCancelled = false;
    private boolean forClient;

    public Order(int clientId, int employeeId, boolean forClient) {
        this.id = Integer.parseInt(UUID.randomUUID().toString());
        this.date = LocalDateTime.now();
        this.clientId = clientId;
        this.employeeId = employeeId;
        this.forClient = forClient;
    }

    public double getCost(){
        double totalCost = 0;
        for(Product p : this.products){
            totalCost += p.getPrice();
        }

        return totalCost;
    }

    public List<Product> getProducts(){
        return this.products;
    }

    public void addProduct(Product product){
        this.products.add(product);
    }

    public void cancell(){
        this.isCancelled = true;
    }

    public int getId(){
        return this.id;
    }

    public LocalDateTime getDate(){
        return this.date;
    }

    public int getClientId(){
        return this.clientId;
    }

    public int getEmployeeId() {
        return employeeId;
    }
}
