package transfertoserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TransferToServer {
    private ServerSocketChannel listener = null;

    public void setup() {
        final InetSocketAddress listenAddr = new InetSocketAddress(9026);
        try {
            listener = ServerSocketChannel.open();
            final ServerSocket ss = listener.socket();
            ss.setReuseAddress(true);
            ss.bind(listenAddr);
            System.out.println("Listening port: " + listener.toString());
        } catch (IOException e) {
            System.out.println("Failed to bind, port: " + listenAddr + " already in use. Cause: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void readData() {
        final ByteBuffer dst = ByteBuffer.allocate(4096);
        try {
            while (true) {
                final SocketChannel conn = listener.accept();
                System.out.println("Accepted: " + conn);
                conn.configureBlocking(true);

                int nread = 0;
                while (nread != -1) {
                    try {
                        nread = conn.read(dst);
                    } catch (IOException e) {
                        e.printStackTrace();
                        nread = -1;
                    }
                    dst.rewind();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
