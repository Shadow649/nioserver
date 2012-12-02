package com.shadow649soft.server.api.http.configuration;

public interface ResourceHandlerConfiguration {

    String getRootPath();
    
    String getDefaultPageName();
    
    String getNotFoundpage();
    
    String getInternalErrorPage();
    
    String getRequestTimeoutPage();

}
