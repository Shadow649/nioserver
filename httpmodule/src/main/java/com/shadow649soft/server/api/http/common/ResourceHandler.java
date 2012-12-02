package com.shadow649soft.server.api.http.common;

import com.shadow649soft.server.impl.http.response.ResourceHandlerException;

public interface ResourceHandler<T> {
    
    T getResource(String requestedResource, MutableHttpResponse response) throws ResourceHandlerException;

}
