package com.shadow649soft.server.impl.application.echo;
import java.nio.channels.SelectionKey;

import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.shadow649soft.server.api.application.ApplicationContext;
import com.shadow649soft.server.impl.application.ApplicationContextImpl;

public class EchoApplicationContext extends ApplicationContextImpl implements ApplicationContext {
    public EchoApplicationContext(EchoConfiguration conf) {
        application = new EchoApplication(conf);
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
    public RemovalListener<? super SelectionKey, ? super Object> getExpiresAction() {
        RemovalListener<SelectionKey, Object> asd = new RemovalListener<SelectionKey, Object>() {

            @Override
            public void onRemoval(RemovalNotification<SelectionKey, Object> removal) {
                application.expiredRequest(removal.getKey());
            }
        };
        return asd;
    }

}
