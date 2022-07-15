package Server;

import java.io.IOException;
import java.util.HashSet;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class ClientsCleaner extends Thread {
    private Server server;
    private Client client_to_delete;

    public ClientsCleaner(Server server, Client client_to_delete) {
        this.server = server;
        this.client_to_delete = client_to_delete;
    }

    public void run() {
        try{
            client_to_delete.close();
            HashSet<Client> connected_clients = this.server.getConnected_clients();
            connected_clients.remove(client_to_delete);
            this.server.setConnected_clients(connected_clients);

            System.out.println("ClientsCleaner: " + connected_clients.size());
        } catch (IOException e){
            e.printStackTrace();
        }
        

    }
}
