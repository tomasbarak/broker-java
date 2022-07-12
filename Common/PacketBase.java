package Common;

import java.io.ByteArrayOutputStream;

public class PacketBase {
    private String id; // 36 bytes
    private Character origin; // Server or Client (1 bytes) 0 = Server, 1 = Client
    private Character destination; // Server or Client (1 bytes) 0 = Server, 1 = Client
    private String data; // Data (32767 bytes)

    public PacketBase(String id, Character origin, Character destination, String data) {
        if (id.length() != 36) {
            throw new IllegalArgumentException("Package id must be 16 bytes long");
        }
        if (data.length() > 60000) {
            throw new IllegalArgumentException("Package data must be less than 32767 bytes long");
        }
        if (data.length() < 0) {
            throw new IllegalArgumentException("Package data must be greater than 0 bytes long");
        }
        if (origin.equals('0') && origin.equals('1')) {
            throw new IllegalArgumentException("Package origin must be 0 or 1");
        }
        if (destination.equals('0') && destination.equals('1')) {
            throw new IllegalArgumentException("Package destination must be 0 or 1");
        }
        if (origin.equals(destination)) {
            throw new IllegalArgumentException("Package origin and destination must be different");
        }
        if (data.length() == 0) {
            throw new IllegalArgumentException("Package data must be greater than 0 bytes long");
        }

        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.data = data;
    }
    
    // Constructor for creating a packet from a string



    public String getId() {
        return id;
    }

    public Character getOrigin() {
        return origin;
    }

    public Character getDestination() {
        return destination;
    }

    public String getData() {
        return data;
    }

    public String packetToString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id);
        sb.append(this.origin);
        sb.append(this.destination);
        sb.append(this.data);
        return sb.toString();
    }
}
