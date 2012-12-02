package com.shadow649soft.server.impl.application.http;

import java.nio.channels.SelectionKey;

import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.shadow649soft.server.api.http.configuration.HttpConfiguration;
import com.shadow649soft.server.impl.application.ApplicationContextImpl;

public class HttpApplicationContext extends ApplicationContextImpl {

    public HttpApplicationContext(HttpConfiguration echoConf) {
        this.application = new HttpApplication(echoConf);
    }

    @Override
    public boolean requestCanExpire() {
        return true;
    }

    @Override
    public long requestExpiresTime() {
        return 2;
    }

    @Override
    public RemovalListener<? super SelectionKey, ? super Object> expireAction() {
        RemovalListener<SelectionKey, Object> asd = new RemovalListener<SelectionKey, Object>() {

            @Override
            public void onRemoval(RemovalNotification<SelectionKey, Object> removal) {
                application.expiredRequest(removal.getKey());
            }
        };
        return asd;
    }

}
