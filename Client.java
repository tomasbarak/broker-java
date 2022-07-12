import java.net.Socket;
import java.util.ArrayList;
import java.io.*;
import java.util.UUID;

public class Client extends Thread {
    private Socket client_socket;
    private String client_id;
    private BufferedReader in;
    private PrintWriter out;
    private boolean isConnected;

    public Client(Socket client_socket) throws IOException {
        this.client_socket = client_socket;
        this.client_id = UUID.randomUUID().toString();
        this.in = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));
        this.out = new PrintWriter(client_socket.getOutputStream(), true);
        this.isConnected = true;

        this.start();
    }
    public static String unEscapeString(String s){
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<s.length(); i++)
            switch (s.charAt(i)){
                case '\n': sb.append("\\n"); break;
                case '\t': sb.append("\\t"); break;
                // ... rest of escape characters
                default: sb.append(s.charAt(i));
            }
        return sb.toString();
    }

    public void run() {
        while (!(client_socket.isClosed())) {
            // Listen for messages from the client
            try {
                String message = this.in.readLine();
                if (message != null) {
                    this.setConnected(true);
                    //System.out.println(unEscapeString(message));
                } else {
                    this.setConnected(false);
                    client_socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void Connect(String host, int port) throws IOException {
        this.client_socket = new Socket(host, port);
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
        return in;
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
