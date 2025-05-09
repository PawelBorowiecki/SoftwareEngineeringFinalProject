package org.example;

import org.example.model.*;
import org.example.repository.ClientRepository;
import org.example.repository.OrderRepository;
import org.example.repository.ProducerRepository;
import org.example.repository.ProductRepository;
import org.example.service.LogRegister;
import org.example.service.OrderService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int option, quantity, day, month, year;
        String type, name, id;
        Scanner scanner = new Scanner(System.in);
        Client client = null;
        ClientRepository clientRepository = new ClientRepository();
        OrderRepository orderRepository = new OrderRepository();
        ProducerRepository producerRepository = new ProducerRepository();
        ProductRepository productRepository = new ProductRepository();
        LogRegister logRegister = new LogRegister();
        OrderService orderService = new OrderService(orderRepository, logRegister);
        Employee employee = new Employee(orderService, productRepository, producerRepository, clientRepository);

        System.out.println("Witaj w systemie do zarzadzania hurtownia.");
        while(true){
            System.out.println("""
                    Podaj co chcesz zrobic.
                    1: zlozenie zamowienia klienta
                    2: anulowanie zamowienia klienta
                    3: dodanie produktu do hurtowni
                    4: usuwanie produktu z hurtowni
                    5: wylistowanie wszystkich klientow
                    6: wylistowanie wszystkich producentow
                    7: wylistowanie wszystkich produktow
                    8: wylistowanie wszystkich zamowien
                    9: wylistowanie zamowien po dacie
                    10: wylistowanie zamowien danego klienta
                    11: wylistowanie wszystkich dostepnych logow
                    12: zapis logow do pliku
                    13: wyczyszczenie logow
                    inny znak: koniec dzialania programu""");
            option = scanner.nextInt();
            if(option == 1){
                System.out.println("Czy klient jest nowy? Wybierz T, jesli tak.");
                type = scanner.next();
                if(type.equalsIgnoreCase("T")){
                    client = new Client();
                }else{
                    System.out.println("Podaj id klienta");
                    id = scanner.next();
                    Optional<Client> optClient = clientRepository.getClient(id);
                    if(optClient.isPresent()){
                        client = optClient.get();
                    }else{
                        System.out.println("Klient o podanym id nie istnieje w bazie.");
                    }
                }
                Order order = employee.addOrder(client);
                List<Product> products = order.getProducts();
                System.out.println("Zlozono zamowienie.\n" + "Produkty zamowienia to:");
                for(Product prod : products){
                    System.out.println(prod.toString());
                }
                System.out.println("Calkowity koszt zamowienia to: " + order.getCost());
            }else if(option == 2){
                System.out.println("Podaj id zamowienia, ktore chcesz anulowac.");
                id = scanner.next();
                boolean operationStatus = employee.removeOrder(id);
                if(operationStatus){
                    System.out.println("Anulowano zamowienie.");
                }
            }else if(option == 3){
                System.out.println("Podaj typ produktu.");
                type = scanner.next();
                System.out.println("Podaj nazwe produktu.");
                name = scanner.next();
                System.out.println("Podaj ilosc produktu");
                quantity = scanner.nextInt();
                employee.addProduct(type, name, quantity);
            }else if(option == 4){
                System.out.println("Podaj typ produktu.");
                type = scanner.next();
                System.out.println("Podaj nazwe produktu.");
                name = scanner.next();
                System.out.println("Podaj ilosc produktu");
                quantity = scanner.nextInt();
                employee.removeProduct(type, name, quantity);
            }else if(option == 5){
                List<Client> clients = clientRepository.getClients();
                for(Client c : clients){
                    System.out.println(c.toString());
                }
            }else if(option == 6){
                HashMap<String,HashMap<String, Producer>> producers = producerRepository.getProducers();
                for(String k : producers.keySet()){
                    System.out.println(k);
                    for(HashMap<String, Producer> producer : producers.values()){
                        for(Producer p : producer.values()){
                            if(p.getProductType().equals(k)){
                                System.out.println(p);
                            }
                        }
                    }
                }
            }else if(option == 7){
                List<Product> products = productRepository.getProducts();
                for(Product p : products){
                    System.out.println(p.toString());
                }
            }else if(option == 8){
                List<Order> orders = orderRepository.getOrders();
                for(Order o : orders){
                    System.out.println(o.toString());
                }
            }else if(option == 9){
                System.out.println("Podaj rok");
                year = scanner.nextInt();
                System.out.println("Podaj miesiac");
                month = scanner.nextInt();
                System.out.println("Podaj dzien");
                day = scanner.nextInt();
                List<Order> ordersFromDate = orderRepository.getOrdersByDate(year, month, day);
                for(Order o : ordersFromDate){
                    System.out.println(o.toString());
                }
            }else if(option == 10){
                System.out.println("Podaj id klienta, ktorego zamowienia chcesz wyswietlic.");
                id = scanner.next();
                List<Order> clientOrders = orderRepository.getOrdersByClientId(id);
                for(Order o : clientOrders){
                    System.out.println(o.toString());
                }
            }else if(option == 11){
                List<Log> logs = logRegister.getlogs();
                for(Log l : logs){
                    System.out.println(l.toString());
                }
            }else if(option == 12){
                logRegister.saveToFile();
            }else if(option == 13){
                logRegister.clear();
                System.out.println("Wyczyszczono logi.");
            }else{
                System.out.println("Dziekujemy za skorzystanie z systemu.");
                break;
            }
        }
    }
}