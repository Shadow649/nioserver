package com.shadow649soft.server.api.http.common;

import java.io.UnsupportedEncodingException;
import java.util.Map;
/**
 * 
 * @author Emanuele Lombardi
 *
 */
public interface MutableHttpRequest extends MutableHttpMessage, HttpRequest {

//    void setCookies(String headerValue);

    boolean removeParameter(String name);

    void setParameter(String name, String value);

    void setParameters(Map<String, String> parameters);

    void setParameters(String queryString);

    void setParameters(String queryString, String encoding)
            throws UnsupportedEncodingException;

    void clearParameters();

    void setMethod(HttpMethod method);

    void setRequestPath(String requestPath);
}
