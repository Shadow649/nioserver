package com.shadow649soft.server.impl.http.response;

import java.util.Date;

import com.shadow649soft.server.api.http.common.Headers;
import com.shadow649soft.server.api.http.common.HttpResponseStatus;
import com.shadow649soft.server.api.http.common.HttpVersion;
import com.shadow649soft.server.api.http.common.MutableHttpResponse;
import com.shadow649soft.server.impl.http.HttpMessage;
import com.shadow649soft.server.impl.http.HttpUtils;
/**
 * 
 * @author Emanuele Lombardi
 *
 */
public class HttpResponseMessage extends HttpMessage implements MutableHttpResponse{
    
    /**
     * 
     */
    private static final long serialVersionUID = -7298953031772566640L;
    private HttpResponseStatus status;
    

    public HttpResponseMessage() {
        setVersion(HttpVersion.HTTP_1_1);
        setHeader(Headers.SERVER, "Simple server");
        setHeader(Headers.DATE, HttpUtils.toHTTPDate(new Date()));
        this.status = null;
    }

    public HttpResponseStatus getStatus() {
        return status;
    }

    public String getStatusMessage() {
        return status.getDescription();
    }

    public void addCookie(String headerValue) {
        throw new UnsupportedOperationException("Not implemented yet");
    }

    public void setStatus(HttpResponseStatus status) {
        this.status = status;
        
    }

}
