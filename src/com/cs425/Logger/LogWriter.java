package com.cs425.Logger;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by surajdeuja on 2/1/15.
 */
public class LogWriter {
    private PrintStream logStream;
    private Date date;
    private DateFormat simpleDateFormat;

    public LogWriter() {
        File logFile = new File("httpd.log");
        try {
            logFile.createNewFile();
            logStream = new PrintStream(new FileOutputStream(new File("httpd.log"), true));
        } catch (IOException e) {
            e.printStackTrace();
        }

        date = new Date();
        simpleDateFormat = new SimpleDateFormat("yy/mm/dd HH:mm:ss");
    }

    protected void writeToStream(String string) {
        logStream.println(simpleDateFormat.format(date) + ": " + string);
        logStream.flush();
    }
}
