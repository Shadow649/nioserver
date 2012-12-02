package com.shadow649soft.server.impl.application;

import java.nio.channels.SelectionKey;
import java.util.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shadow649soft.server.api.application.Application;
import com.shadow649soft.server.api.application.ApplicationContext;
import com.shadow649soft.server.api.request.RequestParser;
import com.shadow649soft.server.api.response.ResponseBufferStorage;
import com.shadow649soft.server.impl.request.RequestParserStorage;

public abstract class ApplicationContextImpl implements ApplicationContext{

    protected Application application;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public ApplicationContextImpl() {
        RequestParserStorage.getInsance(this);
        ResponseBufferStorage.getInsance();
    }

    public RequestParser getRequestParser(SelectionKey key) {
        return RequestParserStorage.getInsance(this).getRequestParse(key);
    }

    public Application getApplication() {
        return application;
    }

    public void removeData(SelectionKey key) {
       RequestParser p = RequestParserStorage.getInsance(this).removeRequestParser(key);
       if(p != null) {
           logger.info("Removed requestParser" + p.hashCode());
       } else {
           logger.info("Request Expired");
       }
       Queue<?> buffers = ResponseBufferStorage.getInsance().remove(key);
       logger.info("Buffer size" + buffers.size() + " removed " + buffers.hashCode());
    }

}