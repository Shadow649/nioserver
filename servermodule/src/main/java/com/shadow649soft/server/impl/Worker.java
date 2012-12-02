package com.shadow649soft.server.impl;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shadow649soft.server.api.application.Application;
import com.shadow649soft.server.api.application.ApplicationContext;
import com.shadow649soft.server.api.request.RequestParser;
import com.shadow649soft.server.api.request.ServerRequest.Status;
import com.shadow649soft.server.impl.application.ApplicationException;
/**
 * This class have the responsibility to serve client request
 * @author Emanuele Lombardi
 *
 */
public class Worker implements Runnable{
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private static Object guard = new Object();
    
    private ApplicationContext ctx;
    private SelectionKey key;
    private RequestParser parser;


    public Worker(SelectionKey key, ApplicationContext ctx) {
        logger.info("Creating worker");
        this.ctx = ctx;
        this.key = key;
        parser = ctx.getRequestParser(key);
        logger.info("worker created");

    }

    public void notifyDataReceived(ByteBuffer data) {
        this.parser.addData(data);
    }

    public void run() {
        Application application = ctx.getApplication();
        if(!parser.isRequestComplete()) {
                parser.parseData();
        }
        if(parser.isRequestComplete()) {
            synchronized (guard) {
                if(parser.getRequest().getStatus() == Status.Working || parser.getRequest().getStatus() == Status.Served) {
                    logger.info("Other thread served the request");
                    return;
                }
                logger.info("Working the request {} {}",parser.getRequest().getId(), parser.getRequest().getStatus());
                parser.getRequest().setStatus(Status.Working);
            }
            application.setInputRequest(parser.getRequest());
            try {
                logger.info("Submitting data to application layer");
                application.notifyReceivedData(key);
            } catch (ApplicationException e) {
                logger.error(e.getMessage(),e);
            }
        }

    }

}
