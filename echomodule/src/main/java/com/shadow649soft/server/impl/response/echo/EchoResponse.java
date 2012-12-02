package com.shadow649soft.server.impl.response.echo;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.Queue;

import com.shadow649soft.server.api.response.Response;

public class EchoResponse implements Response<String> {
    
    String responseMessage;
    Charset charset = Charset.forName("UTF-8");
    
    public EchoResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getMessage() {
        return responseMessage;
    }
    
    public Queue<ByteBuffer> serialize() {
        Queue<ByteBuffer> responseBuffers = new LinkedList<ByteBuffer>();
        String[] splitted = responseMessage.split(";");
        for (String string : splitted) {
            responseBuffers.offer(charset.encode(string));
        }
        return responseBuffers;
    }

}
