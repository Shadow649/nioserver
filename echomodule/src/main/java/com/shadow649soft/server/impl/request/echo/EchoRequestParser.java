package com.shadow649soft.server.impl.request.echo;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shadow649soft.server.api.request.RequestParser;
import com.shadow649soft.server.api.request.ServerRequest;
import com.shadow649soft.server.impl.application.echo.EchoConfiguration;

public class EchoRequestParser implements RequestParser {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private StringBuilder request;
    private Charset charset;
    CharsetDecoder decoder;
    private Queue<ByteBuffer> dataToRead;
    private String endRequest;

    
    public EchoRequestParser(EchoConfiguration conf) {
        charset = Charset.forName("UTF-8");
        decoder = charset.newDecoder();
        request = new StringBuilder();
        this.dataToRead = new ConcurrentLinkedQueue<ByteBuffer>();
        endRequest = conf.getEndRequestWord();
    }

    public boolean isRequestComplete() {
        return request.toString().contains(endRequest);
    }

    public void addData(ByteBuffer buffer) {
        this.dataToRead.add(buffer);
    }

    public ServerRequest<String> getRequest() {
        return new EchoRequest(request.toString().replace(endRequest,""));
    }

    public void parseData() {
        ByteBuffer buffer = null;
        while((buffer = dataToRead.poll()) != null) {
            buffer.flip();
            String decodedString = charset.decode(buffer).toString();
            request.append(decodedString);
            logger.info("current request is:" + request.toString());
        }
    }


}
