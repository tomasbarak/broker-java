package Common;
import java.net.Socket;
import java.io.*;
import java.util.UUID;

public class ClientBase extends Thread {
    private Socket client_socket;
    private String client_id;
    private BufferedReader in;
    private PrintWriter out;
    private boolean isConnected;

    public ClientBase(Socket client_socket) throws IOException {
        this.client_socket = client_socket;
        this.client_id = UUID.randomUUID().toString();
        this.in = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
        this.out = new PrintWriter(client_socket.getOutputStream(), true);
        this.isConnected = true;
    }

    //Default constructor
    public ClientBase() {
        this.client_id = "";
        this.isConnected = false;
    }

    public void Send(String message) {
        this.out.println(message);
    }

    public String Receive() throws IOException {
        return this.in.readLine();
    }

    public void Close() throws IOException {
        this.client_socket.close();
    }

    public Socket getClient_socket() {
        return client_socket;
    }

    public void setClient_socket(Socket client_socket) {
        this.client_socket = client_socket;
    }

    public BufferedReader getIn() {
        return this.in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
}
