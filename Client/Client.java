package Client;

import Common.ClientBase;
import Common.Packet;
import java.net.Socket;
import java.io.*;

public class Client extends ClientBase {

    public Client(Socket client_socket) throws IOException {
        super(client_socket);
        this.start();
    }

    @Override    //no pelotudo
    public void run() {
        while (this.isConnected()) {
            // Listen for messages from the client
            try {
                String message = this.getIn().readLine();
                if (message != null) {
                    this.setConnected(true);
                    Packet received_packet = Packet.fromString(message);
                    System.out.println(received_packet.getId() + " --> " + this.getClient_id() + ": " + received_packet.getData());
                    //this.close();
                } else {
                    this.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
