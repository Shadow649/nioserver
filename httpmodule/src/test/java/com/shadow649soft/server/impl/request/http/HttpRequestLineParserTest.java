package com.shadow649soft.server.impl.request.http;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URISyntaxException;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import com.shadow649soft.server.api.http.common.HttpMethod;
import com.shadow649soft.server.api.http.common.HttpVersion;
import com.shadow649soft.server.impl.http.request.HttpRequestLineParser;
import com.shadow649soft.server.impl.http.request.HttpRequestLineParser.ParserStatus;
import com.shadow649soft.server.impl.http.request.HttpRequestMessage;
import com.shadow649soft.server.impl.http.request.RequestParseException;

public class HttpRequestLineParserTest {
    
    private HttpRequestLineParser parser;
    
    @Before
    public void setUp() {
        this.parser = new HttpRequestLineParser();
    }

    @Test
    public void testParseRequestLine() throws URISyntaxException, RequestParseException {
        String request = "GET /index.html HTTP/1.1\n other lines";
        Scanner scanner = new Scanner(request);
        String requestLine = scanner.nextLine();
        this.parser.parse(requestLine);
        assertEquals(ParserStatus.REQUEST_LINE_OK, parser.getStatus());
        HttpRequestMessage message = this.parser.getRequest();
        assertEquals(HttpVersion.HTTP_1_1, message.getVersion());
        assertEquals("/index.html", message.getRequestPath());
        assertEquals(HttpMethod.GET, message.getMethod());
    }
    
    @Test
    public void testParseRequest() throws RequestParseException {
        String stringRequest= "GET / HTTP/1.1\r\n" +
                "Host: 127.0.0.1:8080\r\n" +
                "Connection: keep-alive\r\n" +
                "Cache-Control: max-age=0\r\n" +
                "User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_6_8) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.64 Safari/537.11\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8\r\n" +
                "Accept-Encoding: gzip,deflate,sdch\r\n" +
                "Accept-Language: en-US,en;q=0.8\r\n" +
                "Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.3\r\n\r\n";
        Scanner scanner = new Scanner(stringRequest);
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            this.parser.parse(line);
        }
        assertEquals(ParserStatus.PARSED,parser.getStatus());
        HttpRequestMessage message = this.parser.getRequest();
        assertNotNull(message);
        assertEquals(8, message.getHeaders().size());
        //check if : is a problem and case insensitive
        assertEquals("127.0.0.1:8080", message.getHeader("host"));
        assertEquals("127.0.0.1:8080", message.getHeader("HosT"));
    }

}
