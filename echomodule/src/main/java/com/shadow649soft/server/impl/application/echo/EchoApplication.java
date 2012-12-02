package com.shadow649soft.server.impl.application.echo;

import java.nio.channels.SelectionKey;

import com.shadow649soft.server.api.request.RequestParser;
import com.shadow649soft.server.impl.application.AbstractApplication;
import com.shadow649soft.server.impl.request.echo.EchoRequest;
import com.shadow649soft.server.impl.request.echo.EchoRequestParser;
import com.shadow649soft.server.impl.response.echo.EchoResponse;

public class EchoApplication extends AbstractApplication {
    
    private EchoConfiguration conf;
    public EchoApplication(EchoConfiguration conf) {
        this.conf = conf;
    }


    public RequestParser getRequestParserInstance() {
        return new EchoRequestParser(conf);
    }


    @Override
    protected void processRequest(SelectionKey key) {
        EchoRequest request = (EchoRequest) this.request;
        String message = request.getMessage();
        //BUILD RESPONSE
        EchoResponse response =  new EchoResponse("ECHO " + message);
        writeResponse(response,key);

    }
    
    

    @Override
    public void expiredRequest(SelectionKey key) {
        EchoResponse expiredRequestResponse = new EchoResponse("Request time expired, you will be disconnected\n\n");
        writeResponse(expiredRequestResponse,key);
    }

}
