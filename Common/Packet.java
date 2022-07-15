package Common;

public class Packet {
    private String id; // 36 bytes
    private Character origin; // Server or Client (1 bytes) 0 = Server, 1 = Client
    private String data; // Data (32767 bytes)

    public Packet(String id, Character origin, String data) {
        if (id.length() != 36) {
            throw new IllegalArgumentException("Package id must be 16 bytes long");
        }
        if (data.getBytes().length > 65498) {
            throw new IllegalArgumentException("Package data must be less than 65498 bytes long");
        }
        if (data.length() < 0) {
            throw new IllegalArgumentException("Package data must be greater than 0 bytes long");
        }
        if (origin.equals('0') && origin.equals('1')) {
            throw new IllegalArgumentException("Package origin must be 0 or 1");
        }
        if (data.length() == 0) {
            throw new IllegalArgumentException("Package data must be greater than 0 bytes long");
        }

        this.id = id;
        this.origin = origin;
        this.data = data;
    }

    // Constructor for creating a packet from a string

    public String getId() {
        return id;
    }

    public Character getOrigin() {
        return origin;
    }

    public String getData() {
        return data;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.id);
        sb.append(this.origin);
        sb.append(this.data);
        return sb.toString();
    }

    public static Packet fromString(String packet_string) {
        if (packet_string.length() <= 37){
            throw new IllegalArgumentException("Packet string must be at least 38 bytes long");
        }

        String id = packet_string.substring(0, 36);
        Character origin = packet_string.charAt(36);
        String data = packet_string.substring(37, packet_string.length());

        return new Packet(id, origin, data);
    }
}
