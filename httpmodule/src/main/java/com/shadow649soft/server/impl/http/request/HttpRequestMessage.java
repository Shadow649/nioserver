package com.shadow649soft.server.impl.http.request;

import java.util.Collections;
import java.util.Map;

import com.shadow649soft.server.api.http.common.HttpMethod;
import com.shadow649soft.server.api.http.common.HttpRequest;
import com.shadow649soft.server.impl.http.HttpMessage;

public class HttpRequestMessage extends HttpMessage implements
        HttpRequest {
    
    /**
     * 
     */
    private static final long serialVersionUID = 4987351027131747489L;
    protected Map<String, String> parameterMap;
    protected HttpMethod method;
    protected String requestPath;

    public boolean containsParameter(String name) {
        return parameterMap.containsKey(name);
    }

    public String getParameter(String name) {
        return parameterMap.get(name);
    }

    public Map<String, String> getParameters() {
        return Collections.unmodifiableMap(parameterMap);
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getRequestPath() {
        return requestPath;
    }



}
