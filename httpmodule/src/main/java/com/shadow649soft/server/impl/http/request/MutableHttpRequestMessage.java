package com.shadow649soft.server.impl.http.request;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Map;

import com.shadow649soft.server.api.http.common.HttpMethod;
import com.shadow649soft.server.api.http.common.MutableHttpRequest;
/**
 * 
 * @author Emanuele Lombardi
 *
 */
public class MutableHttpRequestMessage extends HttpRequestMessage implements
        MutableHttpRequest {
    
    /**
     * 
     */
    private static final long serialVersionUID = 899225235727263732L;
    public static final String DEFAULT_ENCODING = "UTF-8";

    public boolean removeParameter(String name) {
        return this.parameterMap.remove(name) != null;
    }

    public void setParameter(String name, String value) {
        this.parameterMap.put(name, value);

    }

    public void setParameters(Map<String, String> parameters) {
        this.clearParameters();
        this.parameterMap.putAll(parameters);
    }

    public void setParameters(String queryString) {
        try {
            setParameters(queryString, DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(),e);
        }
    }

    public void setParameters(String queryString, String encoding)
            throws UnsupportedEncodingException {
        if (queryString == null || queryString.length() == 0) {
            return;
        }

        int pos = 0;
        while (pos < queryString.length()) {
            int ampPos = queryString.indexOf('&', pos);

            String value;
            if (ampPos < 0) {
                value = queryString.substring(pos);
                ampPos = queryString.length();
            } else {
                value = queryString.substring(pos, ampPos);
            }

            int equalPos = value.indexOf('=');
            if (equalPos < 0) {
                this.setParameter(URLDecoder.decode(value, encoding), "");
            } else {
                this.setParameter(URLDecoder.decode(value
                        .substring(0, equalPos), encoding), URLDecoder.decode(
                        value.substring(equalPos + 1), encoding));
            }

            pos = ampPos + 1;
        }

    }

    public void clearParameters() {
        this.parameterMap.clear();
    }

    public void setMethod(HttpMethod method) {
        this.method = method;

    }

    public void setRequestPath(String requestPath) {
        String[] splitted = requestPath.split("\\?");
        if(splitted.length <= 1) {
            this.requestPath = requestPath;
        } else {
            this.requestPath = splitted[0];
            setParameters(splitted[1]);
        }
    }
}
