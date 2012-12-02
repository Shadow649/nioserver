package com.shadow649soft.echoserver;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class EchoServerTest extends AbstractEchoTest {

    @Test
    public void testEcho() throws Exception {
        Socket socket = openConnection();
        PrintWriter writer = getWriter(socket);
        BufferedReader reader = getReader(socket);
        writer.println("test");
        writer.println("end");
        assertEquals("ECHO test", reader.readLine());
        writer.close();
        reader.close();
        socket.close();
    }
    
    @Test
    public void testExpiredRequest() throws Exception {
        //DEFAULT TIMEOUT IS 2 seconds
        Socket socket = openConnection();
        PrintWriter writer = getWriter(socket);
        BufferedReader reader = getReader(socket);
        writer.println("test");
        assertEquals("Request time expired, you will be disconnected", reader.readLine());
        writer.close();
        reader.close();
        socket.close();
    }

}
