package com.shadow649soft.server.api.http.configuration;
/**
 * 
 * @author Emanuele Lombardi
 *
 */
public interface MutableHttpConfiguration extends HttpConfiguration{
    
    void setKeepAlive(boolean keepAlive);

    void setKeepAliveTimeoutInSecond(int keepAliveSeconds);

    void setResourceHandlerConfiguration(ResourceHandlerConfiguration conf);

}
