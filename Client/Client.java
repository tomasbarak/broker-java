package Client;
import Common.ClientBase;
import java.net.Socket;
import java.io.*;

public class Client extends ClientBase {

    public Client(Socket client_socket) throws IOException {
        super(client_socket);
        this.start();
    }

    public void run() {
        while (!(this.getClient_socket().isClosed())) {
            // Listen for messages from the client
            try {
                String message = this.getIn().readLine();
                if (message != null) {
                    this.setConnected(true);
                    System.out.println("Server --> " + this.getClient_id() + ": " + message);
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
