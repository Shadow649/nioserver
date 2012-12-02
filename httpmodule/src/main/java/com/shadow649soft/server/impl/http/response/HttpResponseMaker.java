package com.shadow649soft.server.impl.http.response;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;

import com.shadow649soft.server.api.http.common.Headers;
import com.shadow649soft.server.api.http.common.HttpResponseStatus;
import com.shadow649soft.server.api.http.common.HttpVersion;
import com.shadow649soft.server.api.http.configuration.HttpConfiguration;
import com.shadow649soft.server.impl.http.HttpUtils;
import com.shadow649soft.server.impl.http.request.HttpRequestMessage;

public class HttpResponseMaker {
    
    private ResponseFileHandler fileHandler;
    private HttpResponseMessage responseMessage;
    private HttpConfiguration conf;


    public HttpResponseMaker(HttpConfiguration conf) throws ResourceHandlerException {
        this.fileHandler = new ResponseFileHandler(conf.getHandlerConf());
        this.conf = conf;

    }
    
    public HttpResponse makeResponse(HttpRequestMessage requestMessage) throws IOException {
        addStandardHeader(requestMessage.getVersion());
        addKeepAliveHeader(requestMessage.isKeepAlive());
        createBody(requestMessage.getRequestPath());
        return new HttpResponse(responseMessage);
    }

    private void addKeepAliveHeader(boolean keepAlive) {
        this.responseMessage.setKeepAlive(keepAlive);
        if(keepAlive) {
            responseMessage.setHeader(Headers.KEEP_ALIVE, String.format("timeout=%s",conf.getKeepAliveSeconds()));
            responseMessage.setHeader(Headers.CONNECTION, Headers.KEEP_ALIVE_VALUE);
        }
    }

    private void addStandardHeader(HttpVersion version) {
        this.responseMessage = new HttpResponseMessage();
        responseMessage.setVersion(version);
        responseMessage.setHeader(Headers.DATE,HttpUtils.toHTTPDate(new Date()));
        responseMessage.setHeader(Headers.SERVER, "asdasdds");
    }
    
    
    private Queue<ByteBuffer> createBody(String filePath)
            throws IOException
        {
            final Queue<ByteBuffer> bodyBuffers = new LinkedList<ByteBuffer>();
            File fileToSend = fileHandler.getResource(filePath,responseMessage);
            addContent(bodyBuffers, fileToSend);
            return bodyBuffers;
        }

    private void addContent(final Queue<ByteBuffer> bodyBuffers, File fileToSend) throws FileNotFoundException, IOException {
        ByteBuffer buffer;
        FileInputStream is = new FileInputStream(fileToSend);
        FileChannel in = is.getChannel();
        int byteCount = 0;
        int readed = 0;
        while ((readed=in.read((buffer=ByteBuffer.allocate(1024)))) > 0) {
            byteCount += readed;
            buffer.flip();
            bodyBuffers.add(buffer);
        }
        in.close();
        is.close();
        this.responseMessage.setHeader(Headers.LAST_MODIFIED, HttpUtils.toHTTPDate(new Date(fileToSend.lastModified())));
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String type = fileNameMap.getContentTypeFor(fileToSend.getName());
        this.responseMessage.setHeader(Headers.CONTENT_TYPE, type );
        this.responseMessage.setHeader(Headers.CONTENT_LENGTH, String.valueOf(byteCount));
        this.responseMessage.setContent(bodyBuffers);
    }

    public HttpResponse makeExpiredRequestResponse() throws IOException {
        addStandardHeader(HttpVersion.HTTP_1_1);
        addKeepAliveHeader(false);
        this.responseMessage.setStatus(HttpResponseStatus.REQUEST_TIMEOUT);
        createBody(conf.getHandlerConf().getRequestTimeoutPage());
        return new HttpResponse(responseMessage);
    }

}
