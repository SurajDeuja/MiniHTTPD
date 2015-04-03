package com.cs425.ResourceServers;

import com.cs425.HttpExceptions.HttpException;

import java.io.OutputStream;

/**
 * Created by surajdeuja on 1/30/15.
 */
public interface ResourceServer {
    public final String root = "www";

    public void getResource(OutputStream outputStream);
}
