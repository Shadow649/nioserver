package com.shadow649soft.server.api.http.configuration;
/**
 * 
 * @author Emanuele Lombardi
 *
 */
public interface MutableResourceHandlerConf extends ResourceHandlerConfiguration {
    
    void setRootPath(String rootPath);
    
    void setDefaultPageName(String defaultPageName);
    
    void setNotFoundpage(String notFoundPage);
    
    void setInternalErrorPage(String internalErroPage);
    
    void setRequestTimeoutPage(String requestTimeoutPage);

}
