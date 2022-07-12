import java.io.*;
import java.net.*;

import Server.Server;
import Client.Client;
import Common.PacketBase;
public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.init(8080);
        
        

        Socket client_socket = new Socket("localhost", 8080);
        Client client = new Client(client_socket);

        PacketBase packet = new PacketBase(
            client.getClient_id(), 
            '1',
            '0', 
            "Hello, from inside of your momma");
        BufferedInputStream packet_in = new BufferedInputStream(client_socket.getInputStream());
        client.getOut().println(packet.packetToString());
    }
}
