package com.shadow649soft.server.impl.http.response;

import java.io.File;

import com.shadow649soft.server.api.http.common.HttpResponseStatus;
import com.shadow649soft.server.api.http.common.MutableHttpResponse;
import com.shadow649soft.server.api.http.common.ResourceHandler;
import com.shadow649soft.server.api.http.configuration.ResourceHandlerConfiguration;
/**
 * 
 * @author Emanuele Lombardi
 *
 */
public class ResponseFileHandler implements ResourceHandler<File> {

    private File rootDirectory;
    DefaultFileHandler defaultFileHander;

    public ResponseFileHandler(ResourceHandlerConfiguration resourceHandlerConfiguration) throws ResourceHandlerException {
        this.rootDirectory = new File(resourceHandlerConfiguration.getRootPath());
        this.defaultFileHander = new DefaultFileHandler(rootDirectory, resourceHandlerConfiguration);
    }

    public ResponseFileHandler(File rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    //RETURN THE FILE OR NULL
    public File getResource(String requestedPath, MutableHttpResponse response) {
        File f = getRequestedFile(requestedPath, response);
        if (f == null) {
            f = defaultFileHander.getDefaultResponse(response.getStatus());
        }
        return f;
    }

    private File getRequestedFile(String requestedPath, MutableHttpResponse response) {
        File requestedResource = null;
        try {
            if (response.getStatus() == null) {
                String path = rootDirectory.getAbsolutePath().concat(requestedPath);
                requestedResource = new File(path);

                if (requestedResource.isDirectory()) {
                    requestedResource = new File(requestedResource, "index.html");
                }
                if (requestedResource.exists()) {
                    response.setStatus(HttpResponseStatus.OK);
                    return requestedResource;
                } else {
                    response.setStatus(HttpResponseStatus.NOT_FOUND);
                    return null;
                }
            }
            return null;
        }

        catch (Throwable e) {
            response.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
            return null;
        }
    }

}
