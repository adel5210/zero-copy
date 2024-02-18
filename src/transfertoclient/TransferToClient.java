import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class TransferToClient {
    public void sendFile() throws IOException {
        System.out.println("Current directory: "+System.getProperty("user.dir"));
        final String host = "localhost";
        final int port = 9026;
        final SocketAddress sad = new InetSocketAddress(host, port);

        final SocketChannel sc = SocketChannel.open();
        sc.connect(sad);
        sc.configureBlocking(true);

        final String fname = "sendfile/test.txt";
        final long fsize = 1L;
        final long sendSize = 4094L;

        final FileChannel fc = new FileInputStream(fname).getChannel();
        final long startTime = System.currentTimeMillis();
        final long nsent = 0;
        long curnset = 0;
        curnset = fc.transferTo(0, fsize, sc);
        System.out.println(new StringBuilder()
                        .append("Total bytes transferred: ").append(curnset)
                        .append("Time taken ms: ").append(System.currentTimeMillis()-startTime)
                .toString());
    }
}
