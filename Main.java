import java.io.*;
import java.net.*;

import Server.Server;
import Client.Client;
import Common.Packet;

public class Main {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.init(8080);

        for (int i = 0; i < 1000; i++) {
            Socket client_socket = new Socket("localhost", 8080);
            Client client = new Client(client_socket);

            Packet packet = new Packet(
                    client.getClient_id(),
                    '1',
                    "Hello, from the motherfuckin' client");
            client.SendPacket(packet);
        }
    }
}

/*
 fe6c 1
 f705 2
 6923 4
 2312 3
 f32f 8
 fece 7
 6726 6
 b81a 5
 */