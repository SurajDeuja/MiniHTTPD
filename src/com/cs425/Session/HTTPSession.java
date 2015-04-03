package com.cs425.Session;

import com.cs425.HttpExceptions.HttpException;
import com.cs425.Logger.Log;
import com.cs425.Request.Header;
import com.cs425.Request.StatusCode;
import com.cs425.ResourceServers.FileServer;

import java.io.*;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by surajdeuja on 1/27/15.
 */
public class HTTPSession {
    private OutputStream outputStream;
    private InputStream inputStream;
    private Header header;
    private Log log;
    private Socket clientSocket;

    public HTTPSession(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            outputStream = clientSocket.getOutputStream();
            inputStream = clientSocket.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        header = new Header();
        log = new Log();
        log.d("Accepted connection from : " + clientSocket.getInetAddress());
    }

    /**
     * Handle the session and write the response
     */
    public void execute() {
        try {
            readHeader();
            respond();
        } catch (HttpException e) {
            respondError(e);
            log.d(e.getErrorMsg());
        } catch (IOException e) {
            log.d(e.getMessage());
        }
    }

    public void respond() {
        FileServer fileServer = new FileServer(header.get("Resource"));
        fileServer.getResource(outputStream);
    }

    public HttpException readHeader() throws HttpException, IOException {
        BufferedReader buffRdr = new BufferedReader(new InputStreamReader(inputStream));

        header = new Header();

        try {
            // Contains the GET request header
            String line = buffRdr.readLine();

            if (line == null) {
                return null;    // Sometimes browser makes multiple request with empty second request
            }

            String[] fields = line.split(" ");

            if (!fields[0].equals("GET")) {
                throw new HttpException("Not supported!", StatusCode.HTTP_STATUS_NOT_SUPPORTED);
            }

            header.put("HttpMethod", fields[0]);
            header.put("Resource", fields[1]);
            header.put("HttpVersion", fields[2]);

            while ((line = buffRdr.readLine()) != null) {
                fields = line.split(": ");
                if (fields.length == 2) {
                    header.put(fields[0].replace(" ", ""), fields[1].replace(" ", ""));
                } else {
                    break;
                }
            }

            log.d("Valid request found!");

        } catch (IOException exHdr) {
            throw new HttpException("Invalid Request", StatusCode.HTTP_STATUS_BAD_REQUEST);
        }

        return null;
    }


    public static String getTimeHttpHeaderFormat() {
        Calendar calendar = Calendar.getInstance();

        DateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        return dateFormat.format(calendar.getTime());
    }

    public void respondError(HttpException exception) {
        String response = exception.getStatusCode().toString() + "\r\n" +
                          "Date: " + getTimeHttpHeaderFormat() + "\r\n" +
                          "\r\n" +
                          exception.getErrorMsg() + "\r\n";

        PrintStream printStream = new PrintStream(outputStream);

        printStream.print(response);
        printStream.flush();

    }
}
