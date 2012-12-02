package com.shadow649soft.server.api.configuration;

import com.shadow649soft.server.api.application.ApplicationContext;

/**
 * 
 * @author Emanuele Lombardi
 *
 */
public interface ServerConfiguration {
   
    
    int getPort();
    
    int getThreadNumber();
    
    ApplicationContext getApplication(ApplicationConfigurator configurator);

}
