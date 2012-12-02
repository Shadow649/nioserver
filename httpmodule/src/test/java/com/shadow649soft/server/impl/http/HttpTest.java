package com.shadow649soft.server.impl.http;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public abstract class HttpTest {
    
    protected Charset charset = Charset.forName("UTF-8");
    
    public static final String completeRequest = "GET / HTTP/1.1\r\n" +
            "Host: 127.0.0.1:8080\r\n" +
            "Connection: keep-alive\r\n" +
            "Cache-Control: max-age=0\r\n" +
            "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11\r\n" +
            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" +
            "Accept-Encoding: gzip,deflate,sdch\r\n" +
            "Accept-Language: en-US,en;q=0.8\r\n" +
            "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.3\r\n\r\n";
    
    public static final String partialRequest= "GET / HTTP/1.1\r\n" +
            "Host: 127.0.0.1:8080\r\n" +
            "Connection: keep-alive\r\n" +
            "Cache-Control: max-age=0\r\n" +
            "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11\r\n" +
            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" +
            "Accept-Encoding: gzip,deflate,sdch\r\n" +
            "Accept-Language: en-US,en;q=0.8\r\n" +
            "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.3\r\n";
    
    protected ByteBuffer getBuffer(String stringRequest) {
        ByteBuffer b = charset.encode(stringRequest);
        b.position(b.limit());
        return b;
    }

}
