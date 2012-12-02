package com.shadow649soft.server.api.response;

import java.nio.ByteBuffer;
import java.util.Queue;

public interface Response<T> {
    T getMessage();
    public Queue<ByteBuffer> serialize();

}
