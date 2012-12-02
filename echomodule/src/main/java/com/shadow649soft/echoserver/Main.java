package com.shadow649soft.echoserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shadow649soft.server.api.application.ApplicationContext;
import com.shadow649soft.server.api.configuration.ApplicationConfigurator;
import com.shadow649soft.server.api.configuration.Configuration;
import com.shadow649soft.server.api.configuration.MutableServerConfiguration;
import com.shadow649soft.server.api.server.Server;
import com.shadow649soft.server.impl.ServerImpl;
import com.shadow649soft.server.impl.application.echo.EchoApplicationContext;
import com.shadow649soft.server.impl.application.echo.EchoConfiguration;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    private static MutableServerConfiguration defaultConfiguration = new MutableServerConfiguration() {
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
        checkArgs(args);
        Server server = new ServerImpl();
        server.init(defaultConfiguration, defaultApplConfigurator);
        server.start();
    }
    private static void checkArgs(String[] args) {
        if(args.length > 0) {
            logger.info("server started with paramter");
            try {
                Integer port = Integer.valueOf(args[0]);
                defaultConfiguration.setPort(port);
            } catch (Exception e) {
                throw new RuntimeException("Wrong parameter port must be integer > 1023");
            }
        }
    }

}
