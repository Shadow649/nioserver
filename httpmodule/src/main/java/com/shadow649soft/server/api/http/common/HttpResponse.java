package com.shadow649soft.server.api.http.common;


public interface HttpResponse extends HttpMessage {

    HttpResponseStatus getStatus();

    String getStatusMessage();
}
