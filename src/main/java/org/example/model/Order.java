package org.example.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Order {
    private String id;
    private LocalDateTime date;
    private List<Product> products;
    private String clientId;
    private String employeeId;
    private boolean isCancelled = false;
    private boolean forClient;

    public Order(String clientId, String employeeId, boolean forClient) {
        this.id = UUID.randomUUID().toString();
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

    public String getId(){
        return this.id;
    }

    public LocalDateTime getDate(){
        return this.date;
    }

    public String getClientId(){
        return this.clientId;
    }

    public String getEmployeeId() {
        return employeeId;
    }
}
