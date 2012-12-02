package com.shadow649soft.server.api.http.configuration;
/**
 * Configuration of resource handler
 * @author Emanuele Lombardi
 *
 */
public interface ResourceHandlerConfiguration {

    String getRootPath();
    
    String getDefaultPageName();
    
    String getNotFoundpage();
    
    String getInternalErrorPage();
    
    String getRequestTimeoutPage();

}
