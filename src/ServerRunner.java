import com.cs425.MiniHttpd.MiniHTTPD;

public class ServerRunner {
    public static final int PORT = 8080;
    public static final String HOSTNAME = "localhost";

    public static void main(String[] args) {
        int port = PORT;
        String hostname = HOSTNAME;
        if (args.length == 2) {
                hostname = args[0];
                port = Integer.parseInt(args[1]);
        }

        if (port != PORT && hostname.equals(HOSTNAME)) {
            MiniHTTPD httpServer = new MiniHTTPD(hostname, port);
            httpServer.start();
        } else {
            MiniHTTPD httpServer = new MiniHTTPD(PORT);
            httpServer.start();
        }
    }
}
