package com.shadow649soft.server.impl.application.configuration;

import com.shadow649soft.server.api.http.configuration.MutableHttpConfiguration;
import com.shadow649soft.server.api.http.configuration.ResourceHandlerConfiguration;
/**
 * 
 * @author Emanuele Lombardi
 *
 */
public class HttpConfigurationImpl implements MutableHttpConfiguration {
    
    private boolean keepAlive;
    private int keepAliveSeconds;
    private ResourceHandlerConfiguration resourceHandlerConf;

    /* (non-Javadoc)
     * @see com.shadow649soft.server.impl.application.configuration.IHttpConfiguration#setKeepAlive(boolean)
     */
    @Override
    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    /* (non-Javadoc)
     * @see com.shadow649soft.server.impl.application.configuration.IHttpConfiguration#setKeepAliveTimeoutInSecond(int)
     */
    @Override
    public void setKeepAliveTimeoutInSecond(int keepAliveSeconds) {
        this.keepAliveSeconds = keepAliveSeconds;
    }
    
    /* (non-Javadoc)
     * @see com.shadow649soft.server.impl.application.configuration.IHttpConfiguration#getKeepAliveSeconds()
     */
    @Override
    public int getKeepAliveSeconds() {
        return this.keepAliveSeconds;
    }
    
    /* (non-Javadoc)
     * @see com.shadow649soft.server.impl.application.configuration.IHttpConfiguration#getKeepAlive()
     */
    @Override
    public boolean getKeepAlive() {
        return this.keepAlive;
    }

    /* (non-Javadoc)
     * @see com.shadow649soft.server.impl.application.configuration.IHttpConfiguration#getHandlerConf()
     */
    @Override
    public ResourceHandlerConfiguration getHandlerConf() {
        return this.resourceHandlerConf;
    }

    @Override
    public void setResourceHandlerConfiguration(ResourceHandlerConfiguration conf) {
        this.resourceHandlerConf = conf;
    }

}
