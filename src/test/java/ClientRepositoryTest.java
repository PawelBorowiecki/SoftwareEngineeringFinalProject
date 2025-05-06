import org.example.model.Client;
import org.example.repository.ClientRepository;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ClientRepositoryTest {
    @Test
    public void testGetClients(){
        ClientRepository clientRepository = new ClientRepository();
        assertEquals(List.of(), clientRepository.getClients());
    }

    @Test
    public void testGetClient(){
        ClientRepository clientRepository = new ClientRepository();
        Client client = new Client();
        clientRepository.addClient(client);
        assertEquals(client, clientRepository.getClient(client.getId()).get());
    }

    @Test
    public void testAddClientWithOneClient(){
        ClientRepository clientRepository = new ClientRepository();
        Client client = new Client();
        clientRepository.addClient(client);
        assertEquals(List.of(client), clientRepository.getClients());
    }

    @Test
    public void testAddClientWithManyClients(){
        ClientRepository clientRepository = new ClientRepository();
        for(int i = 0; i < 10; i++) {
            Client client = new Client();
            clientRepository.addClient(client);
        }
        assertEquals(10, clientRepository.getClients().size());
    }
}