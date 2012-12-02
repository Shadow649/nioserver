package com.shadow649soft.server.api.http.common;

public interface MutableHttpResponse extends MutableHttpMessage, HttpResponse {
    void addCookie(String headerValue);

    void setStatus(HttpResponseStatus status);
}