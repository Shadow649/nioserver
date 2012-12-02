package com.shadow649soft.server.impl.request.echo;

import java.util.UUID;

import com.shadow649soft.server.api.request.ServerRequest;
import com.shadow649soft.server.impl.request.AbstractRequest;

public class EchoRequest extends AbstractRequest<String> implements ServerRequest<String>{
    
    private String message;
    
    public EchoRequest(String message, String endRequest) {
        this.id = UUID.randomUUID();
        if(message.contains(endRequest)) {
            this.status = Status.Accepted;
        } else {
            this.status = Status.Dummy;
        }
        this.message = message.replace(endRequest, "");
    }

    public String getMessage() {
        return message;
    }

}
