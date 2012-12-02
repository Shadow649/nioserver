package com.shadow649soft.server.impl.application;

import static java.nio.channels.SelectionKey.OP_WRITE;

import java.nio.channels.SelectionKey;

import com.shadow649soft.server.api.application.Application;
import com.shadow649soft.server.api.request.ServerRequest;
import com.shadow649soft.server.api.request.ServerRequest.Status;
import com.shadow649soft.server.api.response.Response;
import com.shadow649soft.server.api.response.ResponseBufferStorage;

/**
 * Common application operation
 * @author Emanuele Lombardi
 *
 */
public abstract class AbstractApplication implements Application {
    protected ServerRequest<?> request;


    public void notifyReceivedData(SelectionKey key) throws ApplicationException {
        if(request == null) {
            throw new ApplicationException("No data to process");
        }
        processRequest(key);
    }
    
    protected abstract void processRequest(SelectionKey key);

    public void setInputRequest(ServerRequest<?> request) {
        this.request = request;
        this.request.setStatus(Status.Working);
    }
    
    protected void writeResponse(Response<?> response, SelectionKey key) {
        ResponseBufferStorage.getInsance().put(key, response.serialize());
        notifyReadyToWrite(key);
    }
    

    protected void notifyReadyToWrite(SelectionKey key) {
        key.interestOps( OP_WRITE );
    }


}
