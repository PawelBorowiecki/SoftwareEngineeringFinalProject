package org.example.repository;

import org.example.model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository {
    private List<Client> clients;

    public ClientRepository() {
        this.clients = new ArrayList<>();
    }

    public List<Client> getClients(){
        return this.clients;
    }

    public Optional<Client> getClient(String id){
        return this.clients.stream().filter(c -> c.getId().equals(id)).findFirst();
    }

    public void addClient(Client client){
        this.clients.add(client);
    }
}
