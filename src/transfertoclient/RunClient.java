package transfertoclient;

import java.io.IOException;

public class RunClient {
    public static void main(String[] args) throws IOException {
        new TransferToClient().sendFile();
    }

}