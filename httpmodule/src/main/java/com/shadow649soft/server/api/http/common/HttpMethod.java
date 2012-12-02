package com.shadow649soft.server.api.http.common;


public enum HttpMethod {


    GET(true),

    POST(false),

    PUT(false),

    DELETE(false),
    
    OPTIONS(false),
    
    HEAD(false),

    TRACE(false);


    private boolean allowed;

    private HttpMethod(boolean allowed) {
        this.allowed = allowed;
    }

    public boolean isAllowedByApplication() {
        return allowed;
    }
}
