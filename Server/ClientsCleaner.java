package Server;

import java.util.HashSet;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;

public class ClientsCleaner extends Thread {
    private Server server;

    public ClientsCleaner(Server server) {
        this.server = server;
    }

    public void run() {
        while (!(server.getServer_socket().isClosed())) {
            ReentrantLock lock = new ReentrantLock();

            try {
                lock.lock();
                HashSet<Client> connected_clients_copy = server.getConnected_clients();
                HashSet<Client> connected_clients_copy_reference = new HashSet<Client>();
                connected_clients_copy_reference.addAll(connected_clients_copy);
                // System.out.print("\033[H\033[2J");
                // System.out.println("Connected clients: " + connected_clients_copy.size());
                for (Client client : connected_clients_copy_reference) {
                    if (client.getClient_socket().isClosed()) {
                        connected_clients_copy.remove(client);
                    }
                    // System.out.println("Client " + client.getClient_id() + " is connected: " +
                    // client.isConnected());
                }
                server.setConnected_clients(connected_clients_copy);

                try {
                    this.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } finally {
                lock.unlock();
            }

        }
    }
}
