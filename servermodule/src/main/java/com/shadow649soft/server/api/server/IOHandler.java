package com.shadow649soft.server.api.server;

import java.net.InetAddress;

import com.shadow649soft.server.api.application.ApplicationContext;
/**
 * 
 * @author Emanuele Lombardi
 *
 */
public interface IOHandler extends Runnable{
    
    void init(Server srv, ApplicationContext ctx);
    
    public InetAddress getLocalAddress();
    public int getLocalPort();

    void close();
    

}
