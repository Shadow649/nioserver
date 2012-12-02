package com.shadow649soft.server.configuration.impl;

import com.shadow649soft.server.api.application.ApplicationContext;
import com.shadow649soft.server.api.configuration.ApplicationConfigurator;
import com.shadow649soft.server.api.configuration.ServerConfiguration;
/**
 * 
 * @author Emanuele Lombardi
 *
 * @param <T>
 */
public class ServerConfigurationImpl<T> implements ServerConfiguration {
    
    private int port;
    private int threadNumber;
    private String serverRootPath;

    public int getPort() {
        return port;
    }

    public int getThreadNumber() {
        return threadNumber;
    }

    public String getServerRootPath() {
        return serverRootPath;
    }

    public ApplicationContext getApplication(ApplicationConfigurator configurator) {
        // TODO Auto-generated method stub
        return null;
    }


}
