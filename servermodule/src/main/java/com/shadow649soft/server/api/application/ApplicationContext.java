package com.shadow649soft.server.api.application;

import java.nio.channels.SelectionKey;

import com.google.common.cache.RemovalListener;
import com.shadow649soft.server.api.request.RequestParser;

public interface ApplicationContext {
    
    RequestParser getRequestParser(SelectionKey key);
    
    Application getApplication();

    void removeData(SelectionKey key);

    boolean requestCanExpire();

    long requestExpiresTime();

    RemovalListener<? super SelectionKey, ? super Object> getExpiresAction();

}
