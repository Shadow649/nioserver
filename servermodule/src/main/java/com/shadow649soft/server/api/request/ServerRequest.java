package com.shadow649soft.server.api.request;

public interface ServerRequest<T> {
    
    public static enum Status
    {

        /**
         * Marks the request as received.
         */
        Dummy,

        /**
         * Marks the request as accepted.
         */
        Accepted,

        /**
         * Marks the request as currently in working.
         */
        Working,
        
        /**
         * Marks the request as served.
         */
        Served;

    }
    
    String getId();
    
    Status getStatus();
    
    void setStatus(Status status);
    
    T getMessage();
    

}
