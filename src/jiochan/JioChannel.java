package jiochan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class JioChannel {

    public static void main(String[] args) {
        System.out.println("Processing Jiochannel ...");
        final JioChannel channel = new JioChannel();
        try {
            if (args.length < 3) {
                System.out.println("usage: JioChannel <source> <destination> <mode> \n");
                return;
            }

            if ("1".equals(args[2])) {
                channel.copy(args[0], args[1]);
            } else {
                channel.zeroCopy(args[0], args[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Complete Jiochannel");

    }

    private void zeroCopy(final String from, final String to) throws IOException {
        FileChannel source = null;
        FileChannel destination = null;

        try {
            source = new FileInputStream(from).getChannel();
            destination = new FileOutputStream(to).getChannel();
            source.transferTo(0, source.size(), destination);
        } finally {
            if (null != source) {
                source.close();
            }
            if (null != destination) {
                destination.close();
            }
        }
    }

    private void copy(final String from, final String to) throws IOException {
        final byte[] data = new byte[8 * 1024];
        FileInputStream fis = null;
        FileOutputStream fos = null;
        long bytesToCopy = new File(from).length();
        long bytesCopied = 0;

        try {
            fis = new FileInputStream(from);
            fos = new FileOutputStream(to);

            while (bytesCopied < bytesToCopy) {
                fis.read(data);
                fos.write(data);
                bytesCopied += data.length;
            }

            fos.flush();
        } finally {
            if (null != fis) {
                fis.close();
            }
            if (null != fos) {
                fos.close();
            }
        }
    }
}


