package com.shadow649soft.server.impl.request;

import java.util.UUID;

import com.shadow649soft.server.api.request.ServerRequest;
/**
 * Common request 
 * @author Emanuele Lombardi
 *
 * @param <T>Type of request message
 */
public abstract class AbstractRequest<T> implements ServerRequest<T>{

    protected UUID id;
    protected Status status;

    public AbstractRequest() {
        this.id = UUID.randomUUID();
        this.status = Status.Dummy;
    }

    public String getId() {
        return id.toString();
    }

    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }

}