package com.shadow649soft.server.api.response;

import java.nio.ByteBuffer;
import java.util.Queue;
/**
 * 
 * @author Emanuele Lombardi
 *
 * @param <T> Type of response message
 */
public interface Response<T> {
    T getMessage();
    
    public Queue<ByteBuffer> serialize();

}
