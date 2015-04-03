package com.cs425.HttpExceptions;

import com.cs425.Request.StatusCode;

/**
 * Created by surajdeuja on 1/29/15.
 */
public class HttpException extends Exception {

    protected String errorMsg;
    protected StatusCode statusCode;

    public HttpException(String msg, StatusCode statusCode) {
        this.errorMsg = msg;
        this.statusCode = statusCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }
}

