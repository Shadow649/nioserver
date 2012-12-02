package com.shadow649soft.echoserver;

import com.shadow649soft.server.api.application.ApplicationContext;
import com.shadow649soft.server.api.configuration.ApplicationConfigurator;
import com.shadow649soft.server.api.configuration.Configuration;
import com.shadow649soft.server.api.configuration.ServerConfiguration;
import com.shadow649soft.server.api.server.Server;
import com.shadow649soft.server.impl.ServerImpl;
import com.shadow649soft.server.impl.application.echo.EchoApplicationContext;
import com.shadow649soft.server.impl.application.echo.EchoConfiguration;

public class Main {

    private static ServerConfiguration defaultConfiguration = new ServerConfiguration() {
        
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
    
    private static ApplicationConfigurator defaultApplConfigurator = new ApplicationConfigurator() {
        public ApplicationContext getApplicationContext() {
            return new EchoApplicationContext((EchoConfiguration) getConfiguration());
        }

        @Override
        public Configuration getConfiguration() {
            EchoConfiguration echoConf = new EchoConfiguration();
            echoConf.setEndWord("end");
            return echoConf;
        }
    };
    public static void main(String[] args) {
        Server server = new ServerImpl();
        server.init(defaultConfiguration, defaultApplConfigurator);
        server.start();
    }

}
