package com.shadow649soft.server.impl.http.response;

import java.net.MalformedURLException;

public class ResourceHandlerException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -7626202170373470362L;

    public ResourceHandlerException(String message, MalformedURLException e) {
        super(message, e);
    }

    public ResourceHandlerException(String string) {
        super(string);
    }

}
