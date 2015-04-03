package com.cs425.ResourceServers;

import com.cs425.Request.StatusCode;
import com.cs425.Session.HTTPSession;

import java.io.*;

/**
 * Created by surajdeuja on 1/30/15.
 */
public class FileServer implements ResourceServer {

    private File fileResource;

    public FileServer(String file) {
        if (file.equals("/")) {
            fileResource = new File(root + file + "index.html");
            return;
        }
        fileResource = new File(root + file);
    }

    public static String convertToUTF8(String s) {
        String out = null;

        try {
            out = new String(s.getBytes("UTF8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            return null;
        }

        return out;
    }

    /**
     * Serveres the resource over HTTP/1.1 format over the Output Stream Object
     * @param outputStream stream to write to
     */
    @Override
    public void getResource(OutputStream outputStream) {
        String header = null;

        PrintStream printStream = new PrintStream(outputStream);

        if (fileResource.exists() && fileResource.isFile()) {
            String mimeType = getMimeType(fileResource.getName());

            if (mimeType.contains("text/html")) {
                header = StatusCode.HTTP_STATUS_OK.toString() + "\r\n" +
                        "Date: " + HTTPSession.getTimeHttpHeaderFormat() + "\r\n" +
                        "Content-Type: " + mimeType + "\r\n" +
                        "\r\n";
            } else if (mimeType.contains("image/jpeg")) {
                header = StatusCode.HTTP_STATUS_OK.toString() + "\r\n" +
                        "Date: " + HTTPSession.getTimeHttpHeaderFormat() + "\r\n" +
                        "Content-Type: " + mimeType + "\r\n" +
                        "Content-Length: " + fileResource.length() + "\r\n" +
                        "\r\n";
            } else {
                header = StatusCode.HTTP_STATUS_OK.toString() + "\r\n" +
                        "Date: " + HTTPSession.getTimeHttpHeaderFormat() + "\r\n" +
                        "Content-Type: " + mimeType + "\r\n" +
                        "\r\n";
            }

            try {

                printStream = new PrintStream(outputStream);
                printStream.print(header);

                InputStream inputStream = new FileInputStream(fileResource);
                byte[] buf = new byte[1024];
                int len;
                while ((len = inputStream.read(buf, 0, 1024)) != -1) {
                    outputStream.write(buf, 0, len);
                }

                outputStream.flush();

            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }
        } else {
            header = StatusCode.HTTP_STATUS_NOT_FOUND.toString() + "\r\n" +
                    "Date: " + HTTPSession.getTimeHttpHeaderFormat() + "\r\n" +
                    "Content-Type: text/html; charset=UTF-8" + "\r\n" +
                    "\r\n";
            printStream.print(header);
            printStream.print("<html>" +
                    "<body>" +
                    "<h1>" +
                    "404: Not Found!" +
                    "</h1>" +
                    "</body>" +
                    "</html>" + "\r\n");

            printStream.flush();
        }

    }

    public String getMimeType(String filename) {

        String extension = filename.substring(filename.contains(".") ? filename.indexOf(".") : 0);

        if (extension.equals("")) {
            return "text/plain";
        } else if (extension.equals(".jpg")) {
            return "image/jpeg";
        } else if (extension.equals(".css")) {
            return "text/css";
        } else if (extension.equals(".html") || extension.equals(".htm") || extension.equals(".htmls")) {
            return "text/html; charset=ISO-8859-1";
        } else if (extension.equals(".jpe") || extension.equals(".jpeg") || extension.equals(".jpg")) {
            return "image/jpeg";
        } else if (extension.equals(".txt")) {
            return "text/plain";
        }

        return "text/plain";
    }
}
