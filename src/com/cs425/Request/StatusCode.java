package com.cs425.Request;

/**
 * Created by surajdeuja on 1/29/15.
 */
public enum StatusCode {
    //@todo : Add more status as required
    HTTP_STATUS_OK("HTTP/1.1 200 OK"),
    HTTP_STATUS_BAD_REQUEST("HTTP/1.1 400"),
    HTTP_STATUS_NOT_FOUND("HTTP/1.1 404 NotFound"),
    HTTP_STATUS_NOT_SUPPORTED("HTTP/1.1 501");

    private String status;

    StatusCode(String s) {
        status = s;
    }

    @Override
    public String toString() {
        return status;
    }
    //final String HTTP_STATUS_OK = "HTTP/1.1 200 OK";
    //final String HTTP_STATUS_BAD_REQUEST = "HTTP/1.1 400";
    //final String HTTP_STATUS_UNAUTHORIZED = "HTTP/1.1 403";
    //final String HTTP_STATUS_NOT_FOUND = "HTTP/1.1 404 NotFound";
    //final String HTTP_STATUS_NOT_SUPPORTED = "HTTP/1.1 501";
    //final String P_STATUS_SERVER_ERROR = "HTTP/1.1 500";

}
