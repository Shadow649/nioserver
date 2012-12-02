package com.shadow649soft.server.api.application;

import java.nio.channels.SelectionKey;

import com.shadow649soft.server.api.request.RequestParser;
import com.shadow649soft.server.api.request.ServerRequest;
import com.shadow649soft.server.impl.application.ApplicationException;


public interface Application {
    
    boolean onDataReceived();

    RequestParser getRequestParserInstance();

    void notifyReceivedData(SelectionKey key) throws ApplicationException;

    void setInputRequest(ServerRequest<?> request);

    void expiredRequest(SelectionKey key);
    

}
