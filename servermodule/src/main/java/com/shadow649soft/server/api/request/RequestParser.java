package com.shadow649soft.server.api.request;

import java.nio.ByteBuffer;

public interface RequestParser {
    
    boolean isRequestComplete();
    
    void addData(ByteBuffer buffer);
    
    void parseData();
    
    ServerRequest<?> getRequest();

}
