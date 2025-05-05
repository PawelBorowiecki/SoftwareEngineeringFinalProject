package org.example.repository;

import org.example.model.Producer;

import java.util.HashMap;
import java.util.Optional;

public class ProducerRepository {
    private final HashMap<String,HashMap<String, Producer>> producerMap;

    public ProducerRepository(){
        this.producerMap = new HashMap<>();
    }

    public Optional<Producer> getProducer(String productType, String productName){
        return Optional.ofNullable(producerMap.get(productType).get(productName));
    }

    public boolean addProducer(Producer producer, String productType, String productName){
        if(!producerMap.containsKey(productType)){
            producerMap.put(productType, new HashMap<>());
        }
        if(!producerMap.get(productType).containsKey(productName)){
            producerMap.get(productType).put(productName,producer);
            return true;
        }
        return false;
    }

    private void loadProducerListFromFile(){
        //TODO
    }
}
