package com.shadow649soft.server.impl.request;

import java.nio.channels.SelectionKey;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.shadow649soft.server.api.application.ApplicationContext;
import com.shadow649soft.server.api.request.RequestParser;

public class RequestParserStorage {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private static RequestParserStorage instance;
    private ConcurrentMap<SelectionKey, RequestParser> key2RpStorage;
    private ApplicationContext ctx;
    
    public static RequestParserStorage getInsance(ApplicationContext ctx) {
        if (instance == null) {
            instance = new RequestParserStorage(ctx);
        }
        return instance;
    }
    
    public RequestParserStorage(ApplicationContext ctx) {
        if(ctx.requestCanExpire()) {
        final Cache<SelectionKey, RequestParser> cache = CacheBuilder.newBuilder()
                .concurrencyLevel(1).weakKeys().expireAfterWrite(ctx.requestExpiresTime(), TimeUnit.SECONDS)
                .removalListener(ctx.getExpiresAction())
                .build();
        this.key2RpStorage = cache.asMap();
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            
            @Override
            public void run() {
                cache.cleanUp();
            }
        }, 0, ctx.requestExpiresTime() * 1000);
        } else {
            this.key2RpStorage = new ConcurrentHashMap<SelectionKey, RequestParser>();
        }
        this.ctx = ctx;
    }

    public RequestParser getRequestParse(SelectionKey key) {
        RequestParser p = key2RpStorage.get(key);
        if(p == null) {
            logger.info("Creating RP");

            p = ctx.getApplication().getRequestParserInstance();
            logger.info("Created RP {} key {} ",p.hashCode(), key.hashCode());

            key2RpStorage.put(key, p);
            logger.info("STORED RP {} key {} ",p.hashCode(), key.hashCode());

        }
        return p;
    }
    
    public RequestParser removeRequestParser(SelectionKey key) {
        return key2RpStorage.remove(key);
    }

}
