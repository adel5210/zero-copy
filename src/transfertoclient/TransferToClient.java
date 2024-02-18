package transfertoclient;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class TransferToClient {
    public void sendFile() throws IOException {
        final String pwd = System.getProperty("user.dir");
        System.out.println("Current directory: " + pwd);

        final String host = "localhost";
        final int port = 9026;
        final SocketAddress sad = new InetSocketAddress(host, port);

        final SocketChannel sc = SocketChannel.open();
        sc.connect(sad);
        sc.configureBlocking(true);

        final String fname = "sendFile/test.txt";
        final long fsize = 1L;

        final FileChannel fc = new FileInputStream(fname).getChannel();
        final long startTime = System.currentTimeMillis();
        long curnset = fc.transferTo(0, fsize, sc);
        System.out.println(new StringBuilder()
                .append("Total bytes transferred: ").append(curnset)
                .append(" ")
                .append("Time taken ms: ").append(System.currentTimeMillis() - startTime));
    }
}
