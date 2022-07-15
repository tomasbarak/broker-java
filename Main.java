import java.io.*;
import java.net.*;

import Server.Server;
import Client.Client;
import Common.Packet;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length > 0){
            System.out.println(args[0]);
            if(args[0].equals("server")){
                Server server = new Server();
                server.init(8081);
                System.out.println("Server started");
            } else if(args[0].equals("client")){
                Client client = new Client(new Socket("localhost", 9999));
                client.start();
            }
        }
    }
}