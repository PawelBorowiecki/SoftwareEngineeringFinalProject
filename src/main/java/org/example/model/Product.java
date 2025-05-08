package org.example.model;

import java.util.UUID;

public abstract class Product {
    private double price;
    private String id;
    private String type;
    private String name;

    public Product(double productPrice, String productType, String productName){
        this.price = productPrice;
        this.id =  UUID.randomUUID().toString();
        this.type = productType;
        this.name = productName;
    }
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Product{" +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", name='" + name + ",'" +
                "price=" + price +
                '}';
    }
}
