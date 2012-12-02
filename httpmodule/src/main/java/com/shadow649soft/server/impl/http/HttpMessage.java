package com.shadow649soft.server.impl.http;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

import com.shadow649soft.server.api.http.common.Headers;
import com.shadow649soft.server.api.http.common.HttpVersion;
import com.shadow649soft.server.api.http.common.MutableHttpMessage;

/**
 * Implementation of HttpMessage
 * @author Emanuele Lombardi
 *
 */

public class HttpMessage implements MutableHttpMessage {

    /**
     * 
     */
    private static final long serialVersionUID = -1237349733942229876L;
    private HttpVersion version;
    private String contentType;
    private int contentLength;
    private boolean keepAlive;
    private Map<String, String> headers;
    private Queue<ByteBuffer> content;
    
    public HttpMessage() {
        this.headers = new HashMap<String, String>();
        this.version = HttpVersion.HTTP_1_0;
        this.contentType = "";
        this.contentLength = 0;
        this.keepAlive = false;
        this.content = new LinkedList<ByteBuffer>();
    }
    
    public HttpVersion getVersion() {
        return version;
    }
    
    public boolean isKeepAlive() {
        return keepAlive;
    }

    public String getContentType() {
        return contentType;
    }
    
    public int getContentLength() {
        return contentLength;
    }
    
    public void setContentLength(int contentLength) {
        this.contentLength = contentLength;
    }

    public String getHeader(String name) {
        return headers.get(name.toLowerCase());
    }

    public Map<String, String> getHeaders() {
        return Collections.unmodifiableMap(headers);
    }

    public void setVersion(HttpVersion version) {
        this.version = version;
    }

    public void setContentType(String type) {
        this.contentType = type;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    public boolean removeHeader(String name) {
        return this.headers.remove(name)!= null ? true : false;
    }

    public void setHeader(String name, String value) {
        removeHeader(name);
        setNotableHeaders(name,value);
        this.headers.put(name.toLowerCase(), value);
    }

    private void setNotableHeaders(String name, String value) {
        //Each HTTP header field consists of a case-insensitive field name
        if(name.equalsIgnoreCase(Headers.CONNECTION) && value.equalsIgnoreCase(Headers.KEEP_ALIVE_VALUE)) {
            setKeepAlive(true);
        }
        if(name.equalsIgnoreCase(Headers.CONTENT_LENGTH)) {
            setContentLength(Integer.valueOf(value));
        }
        if(name.equalsIgnoreCase(Headers.CONTENT_TYPE)) {
            setContentType(value);
        }
        
    }

    public void setHeaders(Map<String, String> headers) {
        clearHeaders();
        this.headers.putAll(headers);
    }

    public void clearHeaders() {
        this.headers.clear();
    }

    public Queue<ByteBuffer> getContent() {
        return content;
    }

    public void setContent(Queue<ByteBuffer> content) {
        this.content = content;
    }

}
