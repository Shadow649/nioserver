package com.shadow649soft.server.api.configuration;

/**
 * 
 * @author Emanuele Lombardi
 *
 */
public interface MutableServerConfiguration extends ServerConfiguration{
    
    void setThreads(int threadNumber);
    
    void setPort(int port);
    
    void setRootPath(String serverRootPath);

}
