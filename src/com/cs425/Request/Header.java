package com.cs425.Request;

import java.util.Hashtable;

/**
 * Created by surajdeuja on 1/29/15.
 */
public class Header {
    protected Hashtable<String, String> httpHeader;

    public Header() {
        httpHeader = new Hashtable<String, String>();
    }

    public void put(String field, String value) {
        httpHeader.put(field, value);
    }

    public String get(String field) {
        return httpHeader.get(field);
    }

    public String getHeader() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(StatusCode.HTTP_STATUS_OK);

        for (String key : httpHeader.keySet()) {
            stringBuilder.append(key + ": " + httpHeader.get(key));
        }

        return stringBuilder.toString();
    }
}
