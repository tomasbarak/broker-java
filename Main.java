import java.io.*;
import java.net.*;
public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.init(8080);
        
        /*Socket client_socket = new Socket("localhost", 8080);
        Client client = new Client(client_socket);
        client.start();*/
    }
}
