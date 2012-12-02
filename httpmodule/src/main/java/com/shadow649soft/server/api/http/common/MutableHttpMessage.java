package com.shadow649soft.server.api.http.common;

import java.nio.ByteBuffer;
import java.util.Map;
import java.util.Queue;

/**
 * 
 * @author Emanuele Lombardi
 *
 */
public interface MutableHttpMessage extends HttpMessage {

    void setVersion(HttpVersion version);

    void setContentType(String type);

    void setContentLength(int contentLength);

    void setKeepAlive(boolean keepAlive);

    boolean removeHeader(String name);

    void setHeader(String name, String value);

    void setHeaders(Map<String, String> headers);

    void clearHeaders();

    //void addCookie(Cookie cookie);

    //boolean removeCookie(Cookie cookie);

    //void setCookies(Collection<Cookie> cookies);

    //void clearCookies();

    void setContent(Queue<ByteBuffer> content);
}
