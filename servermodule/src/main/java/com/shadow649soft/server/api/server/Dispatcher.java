package com.shadow649soft.server.api.server;

import java.nio.channels.SocketChannel;
@Deprecated
public interface Dispatcher extends Runnable {
    
    void register(SocketChannel ch);
    
    void register(ReadHandler readHandler);

}
