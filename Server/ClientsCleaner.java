package Server;
import java.util.HashSet;

public class ClientsCleaner extends Thread {
    private Server server;

    public ClientsCleaner(Server server) {
        this.server = server;
    }

    public void run() {
        while (!(server.getServer_socket().isClosed())) {
            HashSet<Client> connected_clients_copy = new HashSet<Client>(server.getConnected_clients());
            //System.out.print("\033[H\033[2J");
            //System.out.println("Connected clients: " + connected_clients_copy.size());
            for (Client client : server.getConnected_clients()) {
                if (client.getClient_socket().isClosed()) {
                    connected_clients_copy.remove(client);
                }
                //System.out.println("Client " + client.getClient_id() + " is connected: " + client.isConnected());
            }
            server.setConnected_clients(connected_clients_copy);

            try{
                this.sleep(500);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
