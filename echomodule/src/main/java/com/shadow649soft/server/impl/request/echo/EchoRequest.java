package com.shadow649soft.server.impl.request.echo;

import java.util.UUID;

import com.shadow649soft.server.api.request.ServerRequest;
import com.shadow649soft.server.impl.request.AbstractRequest;

public class EchoRequest extends AbstractRequest<String> implements ServerRequest<String>{
    
    private String message;
    
    public EchoRequest(String message) {
        this.id = UUID.randomUUID();
        this.status = Status.Dummy;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
