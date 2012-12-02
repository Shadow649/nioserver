package com.shadow649soft.server.api.server;

import com.shadow649soft.server.api.configuration.ApplicationConfigurator;
import com.shadow649soft.server.api.configuration.ServerConfiguration;
/**
 * 
 * @author Emanuele Lombardi
 *
 */
public interface Server {
    
    public static enum Status
    {

        /**
         * Marks the server has been initialized.
         */
        INITIALIZED,

        /**
         * The server is currently running.
         */
        RUNNING,

        /**
         * The server is stopped.
         */
        STOPPED,
        
        /**
         * The server is reloading.
         */
        RELOADING;

    }
    
    public void start();
    
    public void stop();
    
    public void reload();
    
    public void init(ServerConfiguration configuration, ApplicationConfigurator configurator);
    
    public Status getStatus();
    
    public void setStatus(Status status);
    
    ServerConfiguration getServerConfiguration();

}
