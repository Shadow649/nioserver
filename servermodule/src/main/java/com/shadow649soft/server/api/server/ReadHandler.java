package com.shadow649soft.server.api.server;

import java.io.IOException;
import java.nio.channels.SelectionKey;
@Deprecated
public interface ReadHandler {
    
    public int read(SelectionKey key) throws IOException;

}
