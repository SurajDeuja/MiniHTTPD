package com.cs425.Logger;

/**
 * Created by surajdeuja on 2/1/15.
 */
public class Log extends LogWriter {
    public void d(String msg) {
        writeToStream(msg);
    }

    public void e(String msg) {
        writeToStream(msg);
    }
}
