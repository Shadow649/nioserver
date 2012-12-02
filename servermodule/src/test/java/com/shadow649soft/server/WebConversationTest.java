package com.shadow649soft.server;
import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.nio.channels.SelectionKey;

import org.junit.Test;

import com.shadow649soft.server.api.application.Application;
import com.shadow649soft.server.api.application.ApplicationContext;
import com.shadow649soft.server.api.configuration.ApplicationConfigurator;
import com.shadow649soft.server.api.configuration.ServerConfiguration;
import com.shadow649soft.server.api.request.RequestParser;
import com.shadow649soft.server.api.request.ServerRequest;
import com.shadow649soft.server.api.server.Server;
import com.shadow649soft.server.api.server.Server.Status;
import com.shadow649soft.server.impl.IoHandlerImpl;


public class WebConversationTest {
    
    static IoHandlerImpl ioHandler = new IoHandlerImpl();

    @Test
    public void testInitialization() {
        try {
        Server server = mock(Server.class);
        when(server.getStatus()).thenReturn(Status.RUNNING);
        when(server.getServerConfiguration()).thenReturn(new ServerConfiguration() {
            
            @Override
            public int getThreadNumber() {
                // TODO Auto-generated method stub
                return 4;
            }
            
            @Override
            public int getPort() {
                // TODO Auto-generated method stub
                return 8080;
            }

            @Override
            public ApplicationContext getApplication(ApplicationConfigurator configurator) {
                // TODO Auto-generated method stub
                return null;
            }
        });
        RequestParser parser = mock(RequestParser.class);
        ServerRequest request = mock(ServerRequest.class);
        when(parser.isRequestComplete()).thenReturn(true);
        when(parser.getRequest()).thenReturn(request);
        ApplicationContext ctx = mock(ApplicationContext.class);
        when(ctx.getRequestParser(any(SelectionKey.class))).thenReturn(parser);
        when(ctx.getApplication()).thenReturn(mock(Application.class));
        ioHandler.init(server, ctx);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
    
    @Test
    public void failInitialization() {
        try {
            Server server = mock(Server.class);
            ApplicationContext ctx = mock(ApplicationContext.class);
            ioHandler.init(server, ctx);
            fail();
        } catch (Exception e) {
        }
    }
    
    

}
