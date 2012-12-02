package com.shadow649soft.server.impl.http.response;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.shadow649soft.server.api.response.Response;
/**
 * Http response handled by server
 * @author Emanuele Lombardi
 *
 */
public class HttpResponse implements Response<HttpResponseMessage> {
    private HttpResponseMessage responseMessage;
    Charset charset = Charset.forName("UTF-8");


    public HttpResponse(HttpResponseMessage responseMessage) {
        this.responseMessage = responseMessage;
        
    }

    public HttpResponseMessage getMessage() {
        return responseMessage;
    }

    public Queue<ByteBuffer> serialize() {
        Queue<ByteBuffer> responseBuffers = new LinkedList<ByteBuffer>();
        writeStatusLine(responseBuffers);
        writeHeaders(responseBuffers);
        writeBody(responseBuffers);
        return responseBuffers;
    }

    private void writeStatusLine(Queue<ByteBuffer> responseBuffers) {
        /*The first line of a response message is the status-line, consisting
   of the protocol version, a space (SP), the status code, another
   space, a possibly-empty textual phrase describing the status code,
   and ending with CRLF*/
        String statusLine = String.format("%s %s %s%n", responseMessage.getVersion().toString(),
                responseMessage.getStatus().getCode(), responseMessage.getStatus().getDescription());
        responseBuffers.offer(getBuffer(statusLine));
    }
    
    private ByteBuffer getBuffer(String toEncode) {
        return charset.encode(toEncode);
    }

    private void writeBody(Queue<ByteBuffer> responseBuffers) {
        responseBuffers.offer(charset.encode("\r\n"));
        if(responseMessage.getContentLength() > 0) {
            for(ByteBuffer buffer : responseMessage.getContent()) {
                responseBuffers.offer(buffer);
            }
        }
        
    }

    private void writeHeaders(Queue<ByteBuffer> responseBuffers) {
        Map<String,String> headers = responseMessage.getHeaders();
        Set<String> kyes = headers.keySet();
        for (String key : kyes) {
            String header = String.format("%s: %s%n", key, headers.get(key));
            responseBuffers.offer(getBuffer(header));
        }
    }

}
