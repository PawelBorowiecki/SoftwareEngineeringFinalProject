package org.example.repository;

import org.example.model.Producer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ProducerRepository {
    private final HashMap<String,HashMap<String, Producer>> producerMap;

    public ProducerRepository(){
        this.producerMap = new HashMap<>();
        loadProducerListFromFile();
    }

    public HashMap<String,HashMap<String, Producer>> getProducers(){
        return this.producerMap;
    }

    public Optional<Producer> getProducer(String productType, String productName){
        return this.producerMap.get(productType).values().stream().filter(p -> p.getProductsName().contains(productName)).findFirst();
    }

    private void loadProducerListFromFile(){
        File file = new File("producers.txt");
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            boolean isProductType = true;
            String line;
            String productType = null;
            while((line = bufferedReader.readLine()) != null){
                if(isProductType){
                    productType = line;
                    isProductType = false;
                }else{
                    String[] params = line.split(" ");
                    if(params[params.length - 1].equals(";")){
                        isProductType = true;
                    }

                    String[] producerParams = params[2].split("\\.");
                    String[] productsParams = producerParams[2].split(",");
                    Producer producer = new Producer(producerParams[0], producerParams[1]);
                    for(String s : productsParams){
                        producer.produceProduct(s, new Random().nextDouble() * 100, producerParams[1]);
                    }
                    addProducer(producer, productType, productsParams[0]);
                }
            }

        }catch(IOException e){
            throw new RuntimeException("Producers loading failed.");
        }
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
}