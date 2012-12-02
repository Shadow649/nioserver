package com.shadow649soft.server.impl.application.configuration;

import com.shadow649soft.server.api.http.configuration.MutableResourceHandlerConf;
/**
 * 
 * @author Emanuele Lombardi
 *
 */
public class ResourceHandlerConfigurationImpl implements MutableResourceHandlerConf {
    
    private String rootPath;
    
    private String defaultPage;
    
    private String notFoudPage;
    
    private String internalErrorPage;
    
    private String requestTimeoutPage;

    @Override
    public String getRootPath() {
        return rootPath;
    }

    @Override
    public String getDefaultPageName() {
        return defaultPage;
    }

    @Override
    public String getNotFoundpage() {
        return notFoudPage;
    }

    @Override
    public String getInternalErrorPage() {
        return internalErrorPage;
    }

    @Override
    public void setRootPath(String rootPath) {
        this.rootPath = rootPath;
    }

    @Override
    public void setDefaultPageName(String defaultPageName) {
        this.defaultPage = defaultPageName;
    }

    @Override
    public void setNotFoundpage(String notFoundPage) {
        this.notFoudPage = notFoundPage;
    }

    @Override
    public void setInternalErrorPage(String internalErroPage) {
        this.internalErrorPage = internalErroPage;
    }

    @Override
    public String getRequestTimeoutPage() {
        return this.requestTimeoutPage;
    }

    @Override
    public void setRequestTimeoutPage(String requestTimeoutPage) {
        this.requestTimeoutPage = requestTimeoutPage;
    }

    

}
