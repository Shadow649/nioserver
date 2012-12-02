package com.shadow649soft.server.impl;

import com.shadow649soft.server.api.application.ApplicationContext;
import com.shadow649soft.server.api.configuration.ApplicationConfigurator;
import com.shadow649soft.server.api.configuration.ServerConfiguration;
import com.shadow649soft.server.api.server.IOHandler;
import com.shadow649soft.server.api.server.Server;

public class ServerImpl implements Server{
    
    private IOHandler ioHandler;
    private Status status;
    private ServerConfiguration serverConfiguration;

    public void start() {
        new Thread(ioHandler).start();
        
    }

    public void stop() {
        if(this.status == Status.RUNNING) {
            this.status = Status.STOPPED;
        }
        ioHandler.close();
    }

    public void reload() {
        // TODO Auto-generated method stub
        
    }

    public void init(ServerConfiguration configuration, ApplicationConfigurator configurator) {
        if(configuration == null) {
            throw new RuntimeException("Server configuration is necessary");
        }
        this.serverConfiguration = configuration;
        ApplicationContext ctx = configuration.getApplication(configurator);
        this.ioHandler = new IoHandlerImpl();
        ioHandler.init(this,ctx);
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ServerConfiguration getServerConfiguration() {
        return serverConfiguration;
    }
    

}
