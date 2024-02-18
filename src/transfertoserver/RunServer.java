package transfertoserver;

public class RunServer {
    public static void main(String[] args) {
        final TransferToServer dns = new TransferToServer();
        dns.setup();
        dns.readData();
    }
}
