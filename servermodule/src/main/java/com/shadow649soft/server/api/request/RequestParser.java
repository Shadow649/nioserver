package com.shadow649soft.server.api.request;

import java.nio.ByteBuffer;
/**
 * This interface model a class who have the responsibility to parse a request submitted to the server
 * @author Emanuele Lombardi
 *
 */
public interface RequestParser {
    
    boolean isRequestComplete();
    
    void addData(ByteBuffer buffer);
    
    void parseData();
    
    ServerRequest<?> getRequest();

}
