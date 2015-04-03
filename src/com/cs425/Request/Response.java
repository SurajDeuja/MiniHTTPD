package com.cs425.Request;

/**
 * Created by surajdeuja on 1/29/15.
 */
public class Response {
    private String payLoad;
    private Header header;
    private StatusCode statusCode;

    public Response(Header header, String payLoad) {
        this.header = header;
        this.payLoad = payLoad;
    }

    public String getView() {
        // for (String key : he)

        return null;
    }
}
