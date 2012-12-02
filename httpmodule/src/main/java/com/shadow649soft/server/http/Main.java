package com.shadow649soft.server.http;

import com.shadow649soft.server.api.server.Server;
import com.shadow649soft.server.impl.ServerImpl;

public class Main {

    public static void main(String[] args) {
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

}
