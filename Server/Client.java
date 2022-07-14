package Server;

import Common.ClientBase;
import Common.Packet;

import java.net.Socket;
import java.io.*;

public class Client extends ClientBase {

    public Client(Socket client_socket) throws IOException {
        super(client_socket);
        this.start();
    }

    @Override
    public void run() {
        while (!(this.getClient_socket().isClosed())) {
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
                    this.setConnected(false);
                    this.getClient_socket().close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
