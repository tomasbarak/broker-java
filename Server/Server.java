package Server;

import java.io.*;
import java.net.*;
import java.util.HashSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server extends Thread {
    private ServerSocket server_socket;
    private HashSet<Client> connected_clients;
    private ReentrantLock lock = new ReentrantLock(true);

    public void init(int port) throws IOException {
        this.server_socket = new ServerSocket(port);
        this.connected_clients = new HashSet<Client>();
        this.start();
    }

    public void run() {
        while (!server_socket.isClosed()) {
            ReentrantLock lock = new ReentrantLock();
            try {
                lock.lock();
                Socket client_socket = this.server_socket.accept();
                Client client = new Client(client_socket, this);
                HashSet<Client> connected_clients_copy = this.connected_clients;
                connected_clients_copy.add(client);
                this.setConnected_clients(connected_clients_copy);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public HashSet<Client> getConnected_clients() {
        try {
            this.lock.lock();
            return this.connected_clients;
        } finally {
            this.lock.unlock();
        }
    }

    public void setConnected_clients(HashSet<Client> connected_clients) {
        try {
            this.lock.lock();
            this.connected_clients = connected_clients;
        } finally {
            this.lock.unlock();
        }
    }

    public void Close() throws IOException {
        this.server_socket.close();
    }

    public ServerSocket getServer_socket() {
        return server_socket;
    }

    public void setServer_socket(ServerSocket server_socket) {
        this.server_socket = server_socket;
    }

}