package com.shadow649soft.server.impl.http.response;

import java.io.File;

import com.shadow649soft.server.api.http.common.HttpResponseStatus;
import com.shadow649soft.server.api.http.configuration.ResourceHandlerConfiguration;
/**
 * Contains the default file
 * @author Emanuele Lombardi
 *
 */
public class DefaultFileHandler {
    
    private File resourceNotFound;
    private File internalServerError;
    private File requestTimeout;

    public DefaultFileHandler(File rootDirectory, ResourceHandlerConfiguration resourceHandlerConfiguration) {
        resourceNotFound = new File(rootDirectory, resourceHandlerConfiguration.getNotFoundpage());
        internalServerError = new File(rootDirectory, resourceHandlerConfiguration.getInternalErrorPage());
        requestTimeout = new File(rootDirectory, resourceHandlerConfiguration.getRequestTimeoutPage());
    }

    public File getDefaultResponse(HttpResponseStatus status) {
        switch (status.getCode()) {
        case 404:
            return resourceNotFound;
        case 408:
            return requestTimeout;
        default:
            return internalServerError;
        }
    }

}
