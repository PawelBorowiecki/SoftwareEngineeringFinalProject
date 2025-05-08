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
        //TODO rozwarzyc zapisy do plikow i czy konieczne jest odczytywanie z plikow
        //TODO inicjalizacja producentow. Musza byc na poczatku
        //TODO czy dajemy mozliwosc wylistowania wszystkich producentow
        int option, quantity, day, month, year;
        String type, name, id;
        Scanner scanner = new Scanner(System.in);
        Client client = null;
        ClientRepository clientRepository = new ClientRepository();
        OrderRepository orderRepository = new OrderRepository();
        ProducerRepository producerRepository = new ProducerRepository();
        ProductRepository productRepository = new ProductRepository();
        LogRegister logRegister = new LogRegister();
        OrderService orderService = new OrderService(productRepository, orderRepository, logRegister);
        Employee employee = new Employee(orderService, productRepository, producerRepository, clientRepository);

        System.out.println("Witaj w systemie do zarzadzania hurtownia.");
        while(true){
            System.out.println("Podaj co chcesz zrobic.\n" +
                    "1: zlozenie zamowienia klienta\n" +
                    "2: anulowanie zamowienia klienta\n" +
                    "3: dodanie produktu do hurtowni\n" +
                    "4: usuwanie produktu z hurtowni\n" +
                    "5: wylistowanie wszystkich klientow\n" +
                    "6: wylistowanie wszystkich producentow\n" +
                    "7: wylistowanie wszystkich produktow\n" +
                    "8: wylistowanie zamowien po dacie\n" +
                    "9: wylistowanie zamowien danego klienta\n" +
                    "10: wylistowanie wszystkich dostepnych logow\n" +
                    "11: zapis logow do pliku\n" +
                    "12: wyczyszczenie logow\n" +
                    "inny znak: koniec dzialania programu");
            option = scanner.nextInt();
            if(option == 1){
                System.out.println("Czy klient jest nowy? Wybierz T, jesli tak.");
                type = scanner.next();
                if(type.equalsIgnoreCase("T")){
                    client = new Client();
                    clientRepository.addClient(client);
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
                employee.addOrder(client.getId());
            }else if(option == 2){
                System.out.println("Podaj id zamowienia, ktore chcesz anulowac.");
                id = scanner.next();
                employee.removeOrder(id);
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
            }else if(option == 9){
                System.out.println("Podaj id klienta, ktorego zamowienia chcesz wyswietlic.");
                id = scanner.next();
                List<Order> clientOrders = orderRepository.getOrdersByClientId(id);
                for(Order o : clientOrders){
                    System.out.println(o.toString());
                }
            }else if(option == 10){
                List<Log> logs = logRegister.getlogs();
                for(Log l : logs){
                    System.out.println(l.toString());
                }
            }else if(option == 11){
                logRegister.saveToFile();
            }else if(option == 12){
                logRegister.clear();
            }else{
                System.out.println("Dziekujemy za skorzystanie z systemu.");
                break;
            }
        }
    }
}