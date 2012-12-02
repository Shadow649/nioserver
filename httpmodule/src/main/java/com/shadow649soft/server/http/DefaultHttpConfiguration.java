package com.shadow649soft.server.http;

import java.io.IOException;
import java.util.Properties;

import com.shadow649soft.server.api.application.ApplicationContext;
import com.shadow649soft.server.api.configuration.ApplicationConfigurator;
import com.shadow649soft.server.api.configuration.ServerConfiguration;
import com.shadow649soft.server.api.http.configuration.HttpConfiguration;
import com.shadow649soft.server.api.http.configuration.MutableHttpConfiguration;
import com.shadow649soft.server.api.http.configuration.MutableResourceHandlerConf;
import com.shadow649soft.server.impl.application.configuration.HttpConfigurationImpl;
import com.shadow649soft.server.impl.application.configuration.ResourceHandlerConfigurationImpl;
import com.shadow649soft.server.impl.application.http.HttpApplicationContext;
/**
 * This class give the default configurations
 * Further implementations are certainly possibile
 * @author Emanuele Lombardi
 *
 */
public class DefaultHttpConfiguration {
    
    public static ServerConfiguration defaultConfiguration = new ServerConfiguration() {
        
        public int getThreadNumber() {
            return 8;
        }
        
        public int getPort() {
            return 8080;
        }

        public ApplicationContext getApplication(ApplicationConfigurator configurator) {
            return configurator.getApplicationContext();
        }

    };
    
    public static ApplicationConfigurator defaultApplConfigurator = new ApplicationConfigurator() {
        public ApplicationContext getApplicationContext() {
            return new HttpApplicationContext(getConfiguration());
        }
        
        public HttpConfiguration getConfiguration() {
            MutableHttpConfiguration httpConf = new HttpConfigurationImpl();
            httpConf.setKeepAlive(true);
            httpConf.setKeepAliveTimeoutInSecond(5);
            MutableResourceHandlerConf rhc = new ResourceHandlerConfigurationImpl();
            rhc.setDefaultPageName("index.html");
            rhc.setInternalErrorPage("defaultPages/500.html");
            rhc.setNotFoundpage("defaultPages/404.html");
            rhc.setRequestTimeoutPage("/defaultPages/408.html");
            Properties props = new Properties();
            try {
                props.load(this.getClass().getResourceAsStream("/project.properties"));
                rhc.setRootPath(String.format("%s%s",props.get("http.root").toString(),"/root"));
            } catch (IOException e) {
                //TODO throw configuration exception
            }
            
            httpConf.setResourceHandlerConfiguration(rhc);
            return httpConf;
        }
    };

}
