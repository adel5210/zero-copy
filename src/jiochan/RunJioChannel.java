package jiochan;

import java.io.IOException;

public class RunJioChannel {
    public static void main(String[] args) {
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
    }
}
