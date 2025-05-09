package org.example.model;

import org.example.repository.ClientRepository;
import org.example.repository.ProducerRepository;
import org.example.repository.ProductRepository;
import org.example.service.OrderService;

import java.util.Optional;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class Employee {
    private final String id;
    private final OrderService orderService;
    private final ProductRepository productRepository;
    private final ProducerRepository producerRepository;
    private final ClientRepository clientRepository;

    public Employee(OrderService orderService, ProductRepository productRepository, ProducerRepository producerRepository, ClientRepository clientRepository) {
        this.id = UUID.randomUUID().toString();
        this.orderService = orderService;
        this.productRepository = productRepository;
        this.producerRepository = producerRepository;
        this.clientRepository = clientRepository;
    }

    public Order addOrder(Client client){
        String productName, productType;
        int quantity;
        Scanner scanner = new Scanner(System.in);
        Order order = new Order(client.getId(), this.id, true);
        while(true){
            System.out.println("Podaj nazwe produktu, ktory chcesz dodac do zamowienia lub END by zakonczyc.");
            productName = scanner.next();
            if(productName.equalsIgnoreCase("END")){
                break;
            }
            System.out.println("Podaj typ produktu.");
            productType = scanner.next();
            System.out.println("Podaj ilosc produktu.");
            quantity = scanner.nextInt();
            for(int i = 0; i < quantity; i++){
                Optional<Product> product = this.productRepository.getProduct(productName, productType);
                if(product.isPresent()){
                    order.addProduct(product.get());
                    this.productRepository.removeProduct(product.get());
                }else{
                    System.out.println("Nie mamy takiego produktu.");
                }
            }
        }
        this.orderService.placeOrder(order);
        client.addPlacedOrder(order);
        if(this.clientRepository.getClient(client.getId()).isPresent()){
            this.clientRepository.deleteById(client.getId());
            this.clientRepository.addClient(client);
        }else{
            this.clientRepository.addClient(client);
        }
        return order;
    }

    public boolean removeOrder(String orderId){
        return this.orderService.cancellOrder(orderId);
    }

    public void addProduct(String type, String name, int quantity){
        Optional<Producer> producer = this.producerRepository.getProducer(type, name);
        if(producer.isPresent()){
            Tool tool;
            for(int i = 0; i < quantity; i++){
                tool = producer.get().produceProduct(name, new Random().nextDouble() * 100, type);
                this.productRepository.addProduct(tool);
            }
            System.out.println("Dodano produkty.");
        }else{
            System.out.println("Nie znamy producenta, produkujacego takie produkty.");
        }
    }

    public void removeProduct(String type, String name, int quantity){
        for(int i = 0; i < quantity; i++){
            Optional<Product> product = this.productRepository.getProduct(name, type);
            if(product.isPresent()){
                this.productRepository.removeProduct(product.get());
            }else{
                break;
            }
        }
        System.out.println("Zakonczono usuwanie.");
    }
}