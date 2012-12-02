package com.shadow649soft.server.api.http.common;

/**
 * 
 * @author Emanuele Lombardi
 *
 */
public enum HttpVersion {
    /**
     * HTTP 1/1
     */
    HTTP_1_1("HTTP/1.1"),

    /**
     * HTTP 1/0
     */
    HTTP_1_0("HTTP/1.0");

    private final String value;

    private HttpVersion(String value) {
        this.value = value;
    }
    
    public static HttpVersion fromString(String string) {
        if (HTTP_1_1.toString().equalsIgnoreCase(string)) {
            return HTTP_1_1;
        }

        if (HTTP_1_0.toString().equalsIgnoreCase(string)) {
            return HTTP_1_0;
        }

        return null;
    }

    @Override
    public String toString() {
        return value;
    }

}
