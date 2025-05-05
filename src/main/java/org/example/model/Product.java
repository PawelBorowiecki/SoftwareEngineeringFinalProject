package org.example.model;

import java.util.UUID;

public abstract class Product {
    private double price;
    private int id;
    private String type;
    private String name;

    public Product(double productPrice, String productType, String productName){
        this.price = productPrice;
        this.id =  Integer.parseInt(UUID.randomUUID().toString());
        this.type = productType;
        this.name = productName;
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
