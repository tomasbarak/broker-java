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

    @Override
    public void run() {
        while (!(this.getClient_socket().isClosed())) {
            // Listen for messages from the client
            try {
                String message = this.getIn().readLine();
                if (message != null) {
                    this.setConnected(true);
                    Packet received_packet = Packet.fromString(message);
                    //System.out.println(received_packet.getId() + " --> " + this.getClient_id() + ": " + received_packet.getData());
                } else {
                    this.setConnected(false);
                    this.getClient_socket().close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Random disconnection
            if (Math.random() < 0.1) {
                try {
                    this.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
