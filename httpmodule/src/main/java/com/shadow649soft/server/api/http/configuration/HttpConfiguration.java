package com.shadow649soft.server.api.http.configuration;

import com.shadow649soft.server.api.configuration.Configuration;
/**
 * Configuration of Http option
 * @author Emanuele Lombardi
 *
 */
public interface HttpConfiguration extends Configuration {

     int getKeepAliveSeconds();

     boolean getKeepAlive();

     ResourceHandlerConfiguration getHandlerConf();

}