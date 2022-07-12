package Server;
import java.io.*;
import java.net.*;
import java.util.HashSet;

public class Server extends Thread {
    private ServerSocket server_socket;
    private HashSet<Client> connected_clients;
    private ClientsCleaner cleaner;

    public void init(int port) throws IOException {
        this.server_socket = new ServerSocket(port);
        this.connected_clients = new HashSet<Client>();
        this.cleaner = new ClientsCleaner(this);
        this.cleaner.start();
        this.start();
    }

    public void run() {
        while (!server_socket.isClosed()) {
            try {
                Socket client_socket = this.server_socket.accept();
                Client client = new Client(client_socket);
                this.connected_clients.add(client);
                PrintWriter out = new PrintWriter(client_socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public HashSet<Client> getConnected_clients() {
        return connected_clients;
    }

    public void setConnected_clients(HashSet<Client> connected_clients) {
        this.connected_clients = connected_clients;
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