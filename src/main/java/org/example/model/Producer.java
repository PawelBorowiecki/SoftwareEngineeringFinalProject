package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Producer {
    private String name;
    private String productType;
    private List<String> productsName;

    public Producer(String name, String type){
        this.name = name;
        this.productType = type;
        this.productsName = new ArrayList<>();
    }

    public String getName(){
        return name;
    }

    public Tool produceProduct(String name, double price, String type){
        this.productsName.add(name);
        return new Tool(name, price, type);
    }
}
