package org.example.repository;

import org.example.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ProductRepository {
    private final List<Product> productList;

    public ProductRepository(){
        this.productList = new ArrayList<>();
    }

    public List<Product> getProducts(){
        return productList;
    }

    public Optional<Product> getProduct(String name, String type){
        return productList.stream().filter(s -> Objects.equals(s.getName(), name) && Objects.equals(s.getType(), type)).findAny();
    }

    public void addProduct(Product product){
        productList.add(product);
    }

    public void removeProduct(Product product){
        productList.remove(product);
    }
}
