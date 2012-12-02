package com.shadow649soft.server.impl.http.request;

import com.shadow649soft.server.api.http.common.HttpMethod;
import com.shadow649soft.server.api.http.common.HttpVersion;
/**
 * HttpReqest is parsed line by line
 * @author Emanuele Lombardi
 *
 */
public class HttpRequestLineParser {

    private static final String HEADER_SPERATOR = ":";
    private MutableHttpRequestMessage request;

    public static enum ParserStatus
    {

        /**
         * Marks the server has been initialized.
         */
        NEW_REQUEST,

        REQUEST_LINE_OK,

        HEADERS_PARTS_OK,

        HAS_CONTENT_PART,

        CONTENT_PART_OK,

        PARSED;


    }

    private ParserStatus status;


    public HttpRequestLineParser() {
        this.request = null;
        this.status = ParserStatus.NEW_REQUEST;
    }

    public void parse(String line) throws RequestParseException {
        if(request == null) {
            request = new MutableHttpRequestMessage();
        }
        if(status == ParserStatus.NEW_REQUEST) {
            parseRequestLine(line);
        } else if(status == ParserStatus.REQUEST_LINE_OK ) {
            if(!line.equals("")) {
                parseHeaders(line);
            } else {
                status = ParserStatus.HEADERS_PARTS_OK;
            }
        }
        if(request.getContentLength() > 0) {
            status = ParserStatus.HAS_CONTENT_PART;
            parseContentPart(line); 
        } else if(status == ParserStatus.HEADERS_PARTS_OK || status == ParserStatus.CONTENT_PART_OK) {
            status = ParserStatus.PARSED;
        }
    }

    private void parseContentPart(String line) {
        // TODO Auto-generated method stub

    }

    private void parseHeaders(String line) throws RequestParseException {
        /*Each HTTP header field consists of a case-insensitive field name
        followed by a colon (":"), optional whitespace, and the field value.*/
        //TODO COOKIE
        String[] header = splitHeader(line);
        if(header.length == 2) {
            String key = header[0].trim();
            String value = header[1].trim();
            this.request.setHeader(key, value);
        } else {
            throw new RequestParseException("Header is not valid");
        }
    }

    private String[] splitHeader(String line) {
        if(line.indexOf(HEADER_SPERATOR) == line.lastIndexOf(HEADER_SPERATOR)) {
            return line.split(HEADER_SPERATOR);
        } else {
            String[] result = new String[2];
            int firstOccurrenceIndex = line.indexOf(HEADER_SPERATOR);
            result[0] = line.substring(0, firstOccurrenceIndex);
            result[1] = line.substring(firstOccurrenceIndex + 1,line.length());
            return result;
        }
    }

    protected void parseRequestLine(String line) throws RequestParseException {
        /*A request-line begins with a method token, followed by a single space
        (SP), the request-target, another single space (SP), the protocol
        version, and ending with CRLF.*/
        String [] tokens = line.split(" ");
        if(tokens.length == 3) {
            try {
                HttpMethod method = HttpMethod.valueOf(tokens[0]);
                this.request.setMethod(method);
                this.request.setRequestPath(tokens[1]);
                HttpVersion protocolVersion = HttpVersion.fromString(tokens[2]);
                this.request.setVersion(protocolVersion);
                this.status = ParserStatus.REQUEST_LINE_OK; 
            } catch (Exception e) {
                throw new RequestParseException(e.getMessage(),e);
            }
        } else {
            throw new RequestParseException("Request line is not valid");
        }

    }

    public MutableHttpRequestMessage getRequest() {
        return request;
    }

    public ParserStatus getStatus() {
        return status;
    }

    public void destroyRequest() {
        this.request = null;
        this.status = ParserStatus.NEW_REQUEST;
    }

}
