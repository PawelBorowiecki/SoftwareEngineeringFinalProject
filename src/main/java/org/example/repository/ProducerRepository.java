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

    public Optional<Producer> getProducer(String productType, String productName){
        return Optional.ofNullable(producerMap.get(productType).get(productName));
    }

    private void loadProducerListFromFile(){
        //TODO przetestowac
        File file = new File("producers.txt");
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))){
            boolean isProductType = true;
            String line;
            while((line = bufferedReader.readLine()) != null){
                if(isProductType){
                    isProductType = false;
                }else{
                    String[] params = line.split(" ");
                    if(params[params.length - 1].equals(";")){
                        isProductType = true;
                    }

                    String[] producerParams = params[2].split("\\.");
                    String[] productsParams = producerParams[2].split(",");
                    Producer producer = new Producer(producerParams[0], producerParams[1]);
                    List<String> products = new ArrayList<>();
                    for(String s : productsParams){
                        producer.produceProduct(s, new Random().nextDouble() * 100, producerParams[1]);
                    }
                    addProducer(producer, params[0], productsParams[0]);
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