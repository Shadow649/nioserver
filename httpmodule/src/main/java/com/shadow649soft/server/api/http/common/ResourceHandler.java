package com.shadow649soft.server.api.http.common;

import com.shadow649soft.server.impl.http.response.ResourceHandlerException;
/**
 * This class have the responsibility to retrieve a requested resource.
 * @author Emanuele Lombardi
 *
 * @param <T>
 */
public interface ResourceHandler<T> {
    
    T getResource(String requestedResource, MutableHttpResponse response) throws ResourceHandlerException;

}
