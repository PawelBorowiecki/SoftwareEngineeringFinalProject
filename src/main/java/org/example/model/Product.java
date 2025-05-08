package org.example.model;

import java.util.UUID;

public abstract class Product {
    private final double price;
    private final String id;
    private final String type;
    private final String name;

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
