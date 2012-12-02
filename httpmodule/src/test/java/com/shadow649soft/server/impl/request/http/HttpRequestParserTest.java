package com.shadow649soft.server.impl.request.http;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.nio.ByteBuffer;

import org.junit.Before;
import org.junit.Test;

import com.shadow649soft.server.api.request.RequestParser;
import com.shadow649soft.server.impl.http.HttpTest;
import com.shadow649soft.server.impl.http.request.HttpRequestParser;

public class HttpRequestParserTest extends HttpTest{
    
    private RequestParser rp;
    
    @Before
    public void setUp() {
        this.rp = new HttpRequestParser();
    }

    @Test
    public void testIsRequestComplete() throws Exception {
        ByteBuffer b = getBuffer(completeRequest);
        rp.addData(b);
        rp.parseData();
        assertTrue(rp.isRequestComplete());
        
    }
    @Test
    public void testPartialRequest() throws Exception {
        ByteBuffer b = getBuffer(partialRequest);
        rp.addData(b);
        rp.parseData();
        assertFalse(rp.isRequestComplete());
    }

}
