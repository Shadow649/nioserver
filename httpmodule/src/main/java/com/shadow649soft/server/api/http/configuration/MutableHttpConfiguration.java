package com.shadow649soft.server.api.http.configuration;

public interface MutableHttpConfiguration extends HttpConfiguration{
    
    void setKeepAlive(boolean keepAlive);

    void setKeepAliveTimeoutInSecond(int keepAliveSeconds);

    void setResourceHandlerConfiguration(ResourceHandlerConfiguration conf);

}
