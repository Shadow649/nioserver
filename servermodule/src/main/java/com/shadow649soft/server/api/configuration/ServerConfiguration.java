package com.shadow649soft.server.api.configuration;

import com.shadow649soft.server.api.application.ApplicationContext;

public interface ServerConfiguration {
   
    
    int getPort();
    
    int getThreadNumber();
    
    ApplicationContext getApplication(ApplicationConfigurator configurator);

}
