package com.shadow649soft.server.api.configuration;

import com.shadow649soft.server.api.application.ApplicationContext;

public interface ApplicationConfigurator {
    
    ApplicationContext getApplicationContext();
    
    Configuration getConfiguration();

}
