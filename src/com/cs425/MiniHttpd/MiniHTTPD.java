package com.cs425.MiniHttpd;

import com.cs425.Session.HTTPSession;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : Suraj Deuja
 */
public class MiniHTTPD {
    private int port;
    private String hostname;
    private ServerSocket myServerSocket;

    public MiniHTTPD(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public MiniHTTPD(int port) {
        this.port = port;
    }

    /**
     * Starts the httpd server
     */
    public void start() {
        try {
            ServerSocket myServerSocket = new ServerSocket(port);

            // myServerSocket.bind((hostname != null) ? new InetSocketAddress("localhost", port) : new InetSocketAddress(port));

            while (true) {
                // @todo pass the client object to a thread for multi-threaded server
                Socket client = myServerSocket.accept();
                HTTPSession session = new HTTPSession(client);
                session.execute();
                client.close();
            }

        } catch (IOException e) {
            System.err.println("Could not start server on port " + port);
        }
    }

}
