package Server;

import Common.ClientBase;
import Common.Packet;
import Server.ClientsCleaner;

import java.net.Socket;
import java.io.*;

public class Client extends ClientBase {
    private Server server;
    public Client(Socket client_socket, Server server) throws IOException {
        super(client_socket);
        this.server = server;
        this.setConnected(true);
        this.start();
    }

    @Override
    public void run() {
        while (this.isConnected()) {
            // Listen for messages from the client
            try {
                String message = this.getIn().readLine();
                if (message != null) {
                    this.setConnected(true);
                    Packet received_packet = Packet.fromString(message);
                    System.out.println(this.getClient_id() +" <-- " + received_packet.getId() + ": " + received_packet.getData());
                    Packet packet = new Packet(
                            this.getClient_id(),
                            '1',
                            "Hello, from server"
                        );
                    this.SendPacket(packet);
                } else {
                    this.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        ClientsCleaner cleaner = new ClientsCleaner(this.server, this);
        cleaner.start();

    }
}
