package com.shadow649soft.server.impl.http.response;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.shadow649soft.server.api.http.common.Headers;
import com.shadow649soft.server.api.http.common.HttpResponseStatus;
import com.shadow649soft.server.api.http.common.HttpVersion;
import com.shadow649soft.server.api.http.configuration.HttpConfiguration;
import com.shadow649soft.server.http.DefaultHttpConfiguration;
import com.shadow649soft.server.impl.http.HttpTest;
import com.shadow649soft.server.impl.http.request.HttpRequestParser;
import com.shadow649soft.server.impl.http.request.MutableHttpRequestMessage;

public class HttpResponseMakerTest extends HttpTest{
    static  HttpResponseMaker rm;
    HttpRequestParser rp;
    
    @BeforeClass
    public static void setUpClass() throws ResourceHandlerException {
        rm = new HttpResponseMaker((HttpConfiguration) DefaultHttpConfiguration.defaultApplConfigurator.getConfiguration());
    }
    
    @Before
    public void setUp() {
        rp = new HttpRequestParser();
        ByteBuffer b = getBuffer(completeRequest);
        rp.addData(b);
        rp.parseData();
    }

    @Test
    public void test200ok() throws Exception {
        HttpResponse resp =rm.makeResponse(rp.getRequest().getMessage());
        assertNotNull(resp);
        assertTrue(resp.getMessage().isKeepAlive() && resp.getMessage().getHeader(Headers.CONNECTION) != null);
        assertEquals(resp.getMessage().getVersion(),HttpVersion.HTTP_1_1);
        assertEquals(HttpResponseStatus.OK, resp.getMessage().getStatus());
    }
    
    @Test
    public void test404NotFound() throws Exception {
        MutableHttpRequestMessage message = (MutableHttpRequestMessage) rp.getRequest().getMessage();
        message.setRequestPath("/notFound");
        HttpResponse resp =rm.makeResponse(message);
        assertNotNull(resp);
        assertTrue(resp.getMessage().isKeepAlive() && resp.getMessage().getHeader(Headers.CONNECTION) != null);
        assertEquals(resp.getMessage().getVersion(),HttpVersion.HTTP_1_1);
        assertEquals(HttpResponseStatus.NOT_FOUND, resp.getMessage().getStatus());
    }

}
