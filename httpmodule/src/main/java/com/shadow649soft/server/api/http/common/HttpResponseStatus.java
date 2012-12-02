package com.shadow649soft.server.api.http.common;


public class HttpResponseStatus {
    
    /*   7.  Status Code Definitions  . . . . . . . . . . . . . . . . . . . 25
     7.1.   Informational 1xx . . . . . . . . . . . . . . . . . . . . 26
       7.1.1.  100 Continue . . . . . . . . . . . . . . . . . . . . . 26
       7.1.2.  101 Switching Protocols  . . . . . . . . . . . . . . . 27
     7.2.   Successful 2xx  . . . . . . . . . . . . . . . . . . . . . 27
       7.2.1.  200 OK . . . . . . . . . . . . . . . . . . . . . . . . 27
       7.2.2.  201 Created  . . . . . . . . . . . . . . . . . . . . . 27
       7.2.3.  202 Accepted . . . . . . . . . . . . . . . . . . . . . 28
       7.2.4.  203 Non-Authoritative Information  . . . . . . . . . . 28
       7.2.5.  204 No Content . . . . . . . . . . . . . . . . . . . . 28
       7.2.6.  205 Reset Content  . . . . . . . . . . . . . . . . . . 29
     7.3.   Redirection 3xx . . . . . . . . . . . . . . . . . . . . . 29
       7.3.1.  300 Multiple Choices . . . . . . . . . . . . . . . . . 31
       7.3.2.  301 Moved Permanently  . . . . . . . . . . . . . . . . 31
       7.3.3.  302 Found  . . . . . . . . . . . . . . . . . . . . . . 32
       7.3.4.  303 See Other  . . . . . . . . . . . . . . . . . . . . 32
       7.3.5.  305 Use Proxy  . . . . . . . . . . . . . . . . . . . . 33
       7.3.6.  306 (Unused) . . . . . . . . . . . . . . . . . . . . . 33
       7.3.7.  307 Temporary Redirect . . . . . . . . . . . . . . . . 33
     7.4.   Client Error 4xx  . . . . . . . . . . . . . . . . . . . . 33
       7.4.1.  400 Bad Request  . . . . . . . . . . . . . . . . . . . 33
       7.4.2.  402 Payment Required . . . . . . . . . . . . . . . . . 33
       7.4.3.  403 Forbidden  . . . . . . . . . . . . . . . . . . . . 33
       7.4.4.  404 Not Found  . . . . . . . . . . . . . . . . . . . . 34
       7.4.5.  405 Method Not Allowed . . . . . . . . . . . . . . . . 34
       7.4.6.  406 Not Acceptable . . . . . . . . . . . . . . . . . . 34
       7.4.7.  408 Request Timeout  . . . . . . . . . . . . . . . . . 35



Fielding, et al.       Expires September 13, 2012               [Page 3]
 
Internet-Draft              HTTP/1.1, Part 2                  March 2012


       7.4.8.  409 Conflict . . . . . . . . . . . . . . . . . . . . . 35
       7.4.9.  410 Gone . . . . . . . . . . . . . . . . . . . . . . . 35
       7.4.10. 411 Length Required  . . . . . . . . . . . . . . . . . 36
       7.4.11. 413 Request Representation Too Large . . . . . . . . . 36
       7.4.12. 414 URI Too Long . . . . . . . . . . . . . . . . . . . 36
       7.4.13. 415 Unsupported Media Type . . . . . . . . . . . . . . 36
       7.4.14. 417 Expectation Failed . . . . . . . . . . . . . . . . 36
       7.4.15. 426 Upgrade Required . . . . . . . . . . . . . . . . . 37
     7.5.   Server Error 5xx  . . . . . . . . . . . . . . . . . . . . 37
       7.5.1.  500 Internal Server Error  . . . . . . . . . . . . . . 37
       7.5.2.  501 Not Implemented  . . . . . . . . . . . . . . . . . 37
       7.5.3.  502 Bad Gateway  . . . . . . . . . . . . . . . . . . . 37
       7.5.4.  503 Service Unavailable  . . . . . . . . . . . . . . . 38
       7.5.5.  504 Gateway Timeout  . . . . . . . . . . . . . . . . . 38
       7.5.6.  505 HTTP Version Not Supported . . . . . . . . . . . . 38*/
    
    public static final HttpResponseStatus CONTINUE = new HttpResponseStatus(100, "Continue");

    public static final HttpResponseStatus SWITCHING_PROTOCOLS = new HttpResponseStatus(101, "Switching Protocols");

    // Successful status codes

    public static final HttpResponseStatus OK = new HttpResponseStatus(200, "OK");

    public static final HttpResponseStatus CREATED = new HttpResponseStatus(201, "Created");

    public static final HttpResponseStatus ACCEPTED = new HttpResponseStatus(202, "Accepted");

    public static final HttpResponseStatus NON_AUTHORITATIVE = new HttpResponseStatus(203, "Non-Authoritative Information");

    public static final HttpResponseStatus NO_CONTENT = new HttpResponseStatus(204, "No Content");

    public static final HttpResponseStatus RESET_CONTENT = new HttpResponseStatus(205, "Reset Content");

    // Redirection status codes

    public static final HttpResponseStatus MULTIPLE_CHOICES = new HttpResponseStatus(300, "Multiple Choices");

    public static final HttpResponseStatus MOVED_PERMANENTLY = new HttpResponseStatus(301, "Moved Permanently");

    public static final HttpResponseStatus FOUND = new HttpResponseStatus(302, "Found");

    public static final HttpResponseStatus SEE_OTHER = new HttpResponseStatus(303, "See Other");

    public static final HttpResponseStatus USE_PROXY = new HttpResponseStatus(305, "Use Proxy");

    public static final HttpResponseStatus TEMPORARY_REDIRECT = new HttpResponseStatus(307, "Temporary Redirect");

    // Client error codes

    public static final HttpResponseStatus BAD_REQUEST = new HttpResponseStatus(400, "Bad Request");

    public static final HttpResponseStatus PAYMENT_REQUIRED = new HttpResponseStatus(402, "Payment Required");

    public static final HttpResponseStatus FORBIDDEN = new HttpResponseStatus(403, "Forbidden");

    public static final HttpResponseStatus NOT_FOUND = new HttpResponseStatus(404, "Not Found");

    public static final HttpResponseStatus METHOD_NOT_ALLOWED = new HttpResponseStatus(405, "Method Not Allowed");

    public static final HttpResponseStatus NOT_ACCEPTABLE = new HttpResponseStatus(406, "Not Acceptable");

    public static final HttpResponseStatus REQUEST_TIMEOUT = new HttpResponseStatus(408, "Request Time-out");

    public static final HttpResponseStatus CONFLICT = new HttpResponseStatus(409, "Conflict");

    public static final HttpResponseStatus GONE = new HttpResponseStatus(410,"Gone");

    public static final HttpResponseStatus LENGTH_REQUIRED = new HttpResponseStatus(411, "Length Required");

    public static final HttpResponseStatus REQUEST_ENTITY_TOO_LARGE = new HttpResponseStatus(413, "Request Entity Too Large");

    public static final HttpResponseStatus REQUEST_URI_TOO_LONG = new HttpResponseStatus(414, "Request-URI Too Large");

    public static final HttpResponseStatus UNSUPPORTED_MEDIA_TYPE = new HttpResponseStatus(415, "Unsupported Media Type");

    public static final HttpResponseStatus REQUEST_RANGE_NOT_SATISFIABLE = new HttpResponseStatus(416, "Requested range not satisfiable");

    public static final HttpResponseStatus EXPECTATION_FAILED = new HttpResponseStatus(417, "Expectation Failed");

    public static final HttpResponseStatus UPGRADE_REQUIRED = new HttpResponseStatus(426, "Upgrade Required");

    // Server error codes

    public static final HttpResponseStatus INTERNAL_SERVER_ERROR = new HttpResponseStatus(500, "Internal Server Error");

    public static final HttpResponseStatus NOT_IMPLEMENTED = new HttpResponseStatus(501, "Not Implemented");

    public static final HttpResponseStatus BAD_GATEWAY = new HttpResponseStatus(502, "Bad Gateway");

    public static final HttpResponseStatus SERVICE_UNAVAILABLE = new HttpResponseStatus(503, "Service Unavailable");

    public static final HttpResponseStatus GATEWAY_TIMEOUT = new HttpResponseStatus(504, "Gateway Time-out");

    public static final HttpResponseStatus HTTP_VERSION_NOT_SUPPORTED = new HttpResponseStatus(505, "HTTP Version not supported");

    private final int code;
    private final transient String description;


    /**
     * @return The response code of this status
     */
    public int getCode() {
        return code;
    }

    /**
     * @return A description of this status
     */
    public String getDescription() {
        return description;
    }

    /**
     * A string description of this status
     */
    @Override
    public String toString() {
        return code + " " + description;
    }

    private HttpResponseStatus(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
