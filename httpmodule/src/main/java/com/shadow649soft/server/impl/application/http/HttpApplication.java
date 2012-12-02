package com.shadow649soft.server.impl.application.http;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import com.shadow649soft.server.api.application.Application;
import com.shadow649soft.server.api.http.common.HttpVersion;
import com.shadow649soft.server.api.http.configuration.HttpConfiguration;
import com.shadow649soft.server.api.request.RequestParser;
import com.shadow649soft.server.api.request.ServerRequest.Status;
import com.shadow649soft.server.api.response.ResponseBufferStorage;
import com.shadow649soft.server.impl.application.AbstractApplication;
import com.shadow649soft.server.impl.http.request.HttpRequest;
import com.shadow649soft.server.impl.http.request.HttpRequestMessage;
import com.shadow649soft.server.impl.http.request.HttpRequestParser;
import com.shadow649soft.server.impl.http.response.HttpResponse;
import com.shadow649soft.server.impl.http.response.HttpResponseMaker;
import com.shadow649soft.server.impl.http.response.ResourceHandlerException;

public class HttpApplication extends AbstractApplication implements Application {

    private int keepAliveTimeOut;
    private boolean enableKeepAlive;
    private HttpResponseMaker responseMaker;

    public HttpApplication(HttpConfiguration conf) {
        this.keepAliveTimeOut = conf.getKeepAliveSeconds();
        this.enableKeepAlive = conf.getKeepAlive();
        try {
            this.responseMaker = new HttpResponseMaker(conf);
        } catch (ResourceHandlerException e) {
            //TODO LOG
        }
    }

    public RequestParser getRequestParserInstance() {
        return new HttpRequestParser();
    }

    @Override
    protected void processRequest(SelectionKey key) {
        try {
            HttpRequest request = (HttpRequest) this.request;
            HttpRequestMessage message = request.getMessage();
            try {
                setKeepAlive(message, key);
            } catch (SocketException e) {
                //TODO LOG
            }
            HttpResponse resp = responseMaker.makeResponse(request.getMessage());
            ResponseBufferStorage.getInsance().put(key, resp.serialize());
            notifyReadyToWrite(key);
            request.setStatus(Status.Served);
        } catch (Throwable e) {
            //TODO ERROR 500
            e.printStackTrace();
        }

    }

    private void setKeepAlive(HttpRequestMessage message, SelectionKey key) throws SocketException {
        if (enableKeepAlive && message.getVersion() == HttpVersion.HTTP_1_1 && message.isKeepAlive()) {
            setKeepAlive(true, key);
        }

    }

    private void setKeepAlive(boolean value, SelectionKey key) throws SocketException {
        SocketChannel socketChannel = (SocketChannel) key.channel();
        Socket socket = socketChannel.socket();
        socket.setKeepAlive(value);
        socket.setSoTimeout(keepAliveTimeOut);
    }

    @Override
    public void expiredRequest(SelectionKey key) {
        if (ResponseBufferStorage.getInsance().get(key) == null) {
            try {
                HttpResponse response = responseMaker.makeExpiredRequestResponse();
                setKeepAlive(false, key);
                writeResponse(response, key);
            } catch (SocketException e) {
                //TODO LOG
            } catch (IOException e) {
                // TODO ERROR 500
            }
        }
    }

}