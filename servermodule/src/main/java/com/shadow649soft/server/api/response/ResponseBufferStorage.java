package com.shadow649soft.server.api.response;

import static java.nio.ByteBuffer.allocate;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class ResponseBufferStorage {
    
    public static final ByteBuffer EOM = allocate( 0 );
    
    private static ResponseBufferStorage instance;
    private ConcurrentHashMap<SelectionKey, Queue<ByteBuffer>> key2ResponseBufferStorage;
    
    public ResponseBufferStorage() {
        this.key2ResponseBufferStorage = new ConcurrentHashMap<SelectionKey, Queue<ByteBuffer>>();
    }

    public static ResponseBufferStorage getInsance() {
        if (instance == null) {
            instance = new ResponseBufferStorage();
        }
        return instance;
    }
    
    public void put(SelectionKey key,Queue<ByteBuffer> q) {
        q.offer(EOM);
        if(!key2ResponseBufferStorage.containsKey(key)){
            key2ResponseBufferStorage.put(key, q);
        } else {
            Queue<ByteBuffer> current = key2ResponseBufferStorage.get(key);
            current.addAll(q);
        }
    }
    
    public Queue<ByteBuffer> get(SelectionKey key) {
        return this.key2ResponseBufferStorage.get(key);
    }
    
    public Queue<?> remove(SelectionKey key) {
        return key2ResponseBufferStorage.remove(key);
    }

}