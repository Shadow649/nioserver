package com.shadow649soft.server.impl.http.request;

public class RequestParseException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public RequestParseException(String message) {
        super(message);
    }
    
    public RequestParseException(String message, Throwable e) {
        super(message, e);
    }

}
