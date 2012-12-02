package com.shadow649soft.server.http;

import java.io.IOException;
import java.util.Properties;

import com.shadow649soft.server.api.application.ApplicationContext;
import com.shadow649soft.server.api.configuration.ApplicationConfigurator;
import com.shadow649soft.server.api.configuration.MutableServerConfiguration;
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
    
    public static MutableServerConfiguration defaultConfiguration = new MutableServerConfiguration() {
        private int threadNumber = 8;
        private int port = 8080;
        
        public int getThreadNumber() {
            return threadNumber;
        }
        
        public int getPort() {
            return port;
        }

        public ApplicationContext getApplication(ApplicationConfigurator configurator) {
            return configurator.getApplicationContext();
        }
        
        @Override
        public void setThreads(int threadNumber) {
            this.threadNumber= threadNumber;
        }
        
        @Override
        public void setRootPath(String serverRootPath) {
            //TODO
        }
        
        @Override
        public void setPort(int port) {
            this.port = port;
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
