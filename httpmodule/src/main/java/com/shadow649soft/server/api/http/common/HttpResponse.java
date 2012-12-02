package com.shadow649soft.server.api.http.common;

/**
 * Representation of Http Reseponse
 * @author Emanuele Lombardi
 *
 */
public interface HttpResponse extends HttpMessage {

    HttpResponseStatus getStatus();

    String getStatusMessage();
}
