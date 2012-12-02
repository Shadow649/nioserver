package com.shadow649soft.echoserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;

import com.shadow649soft.server.api.application.ApplicationContext;
import com.shadow649soft.server.api.configuration.ApplicationConfigurator;
import com.shadow649soft.server.api.configuration.Configuration;
import com.shadow649soft.server.api.configuration.ServerConfiguration;
import com.shadow649soft.server.api.server.Server;
import com.shadow649soft.server.impl.ServerImpl;
import com.shadow649soft.server.impl.application.echo.EchoApplicationContext;
import com.shadow649soft.server.impl.application.echo.EchoConfiguration;

public abstract class AbstractEchoTest {

    static ServerConfiguration defaultConfiguration = new ServerConfiguration() {

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

    static ApplicationConfigurator defaultApplConfigurator = new ApplicationConfigurator() {
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

    Server server;

    @Before
    public void setUp() throws Exception {
        server = new ServerImpl();
        server.init(defaultConfiguration, defaultApplConfigurator);
        new Thread(new Runnable() {
            
            @Override
            public void run() {
                server.start();
            }
        }).start();
    }

    @After
    public void tearDown() throws Exception {
        server.stop();
        Thread.sleep(1000);
    }

    public Socket openConnection() throws UnknownHostException, IOException {
        Socket echoSocket = null;
        echoSocket = new Socket("127.0.0.1", 8080);
        return echoSocket;
    }

    public PrintWriter getWriter(Socket socket) throws IOException {
        return new PrintWriter(socket.getOutputStream(), true);
    }
    
    public BufferedReader getReader(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }



}
