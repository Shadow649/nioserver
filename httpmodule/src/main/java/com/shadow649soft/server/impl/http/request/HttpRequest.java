package com.shadow649soft.server.impl.http.request;

import com.shadow649soft.server.impl.request.AbstractRequest;

public class HttpRequest extends AbstractRequest<HttpRequestMessage> {
    
    private HttpRequestMessage message;
    
    public HttpRequest() {
        this.message = null;
        setStatus(Status.Dummy);
    }
    
    public HttpRequest(HttpRequestMessage message) {
        this.message = message;
    }

    public HttpRequestMessage getMessage() {
        return message;
    }

}