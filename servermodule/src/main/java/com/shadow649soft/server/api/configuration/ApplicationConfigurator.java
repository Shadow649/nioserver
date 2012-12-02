package com.shadow649soft.server.api.configuration;

import com.shadow649soft.server.api.application.ApplicationContext;

/**
 * Application Configurator
 * it store the application context initialized by a configuration Parser
 * @author Emanuele Lombardi
 *
 */

public interface ApplicationConfigurator {
    
    ApplicationContext getApplicationContext();
    
    Configuration getConfiguration();

}
