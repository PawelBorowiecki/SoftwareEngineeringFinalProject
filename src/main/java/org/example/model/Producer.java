package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Producer {
    private final String name;
    private final String productType;
    private final List<String> productsName;

    public Producer(String name, String type){
        this.name = name;
        this.productType = type;
        this.productsName = new ArrayList<>();
    }

    public String getProductType(){
        return this.productType;
    }

    public List<String> getProductsName(){
        return this.productsName;
    }

    public Tool produceProduct(String name, double price, String type){
        this.productsName.add(name);
        return new Tool(name, price, type);
    }

    @Override
    public String toString() {
        return "Producer{" +
                "name='" + name + '\'' +
                ", productType='" + productType + '\'' +
                ", productsName=" + productsName +
                '}';
    }
}
