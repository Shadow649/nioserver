package com.shadow649soft.server.api.http.common;
/**
 * 
 * @author Emanuele Lombardi
 *
 */
public interface MutableHttpResponse extends MutableHttpMessage, HttpResponse {
    void addCookie(String headerValue);

    void setStatus(HttpResponseStatus status);
}