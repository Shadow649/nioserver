package com.shadow649soft.server.http;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shadow649soft.server.api.server.Server;
import com.shadow649soft.server.impl.ServerImpl;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    public static void main(String[] args) {
        checkArgs(args);
        final Server server = new ServerImpl();
        server.init(DefaultHttpConfiguration.defaultConfiguration, DefaultHttpConfiguration.defaultApplConfigurator);
        server.start();
        
        java.lang.Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                server.stop();
            }
        });
    }
    private static void checkArgs(String[] args) {
        if(args.length > 0) {
            logger.info("server started with paramter");
            try {
                Integer port = Integer.valueOf(args[0]);
                DefaultHttpConfiguration.defaultConfiguration.setPort(port);
            } catch (Exception e) {
                throw new RuntimeException("Wrong parameter port must be integer > 1023");
            }
        }
    }

}
