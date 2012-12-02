package com.shadow649soft.server.api.http.common;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Queue;
/**
 * Basic Http Message representation
 * @author Emanuele Lombardi
 *
 */
public interface HttpMessage extends Serializable {

    HttpVersion getVersion();

    boolean isKeepAlive();

    String getContentType();

    int getContentLength();

    String getHeader(String name);

    Map<String, String> getHeaders();

    //Set<Cookie> getCookies();

    Queue<ByteBuffer> getContent();
}