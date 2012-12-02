package com.shadow649soft.server.api.http.common;

import java.util.Map;


public interface HttpRequest extends HttpMessage {

    HttpMethod getMethod();

    String getRequestPath();

    boolean isKeepAlive();
    
    String getParameter(String name);

    Map<String, String> getParameters();

}
