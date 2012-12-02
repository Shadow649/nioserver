package com.shadow649soft.server.api.application;

import java.nio.channels.SelectionKey;

import com.shadow649soft.server.api.request.RequestParser;
import com.shadow649soft.server.api.request.ServerRequest;
import com.shadow649soft.server.impl.application.ApplicationException;

/**
 * Representation of application handled by server
 * @author Emanuele Lombardi
 *
 */

public interface Application {

    RequestParser getRequestParserInstance();

    void notifyReceivedData(SelectionKey key) throws ApplicationException;

    void setInputRequest(ServerRequest<?> request);

    void expiredRequest(SelectionKey key);
    

}
