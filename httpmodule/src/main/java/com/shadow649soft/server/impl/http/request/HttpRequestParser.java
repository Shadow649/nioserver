package com.shadow649soft.server.impl.http.request;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shadow649soft.server.api.request.RequestParser;
import com.shadow649soft.server.api.request.ServerRequest;
import com.shadow649soft.server.api.request.ServerRequest.Status;
import com.shadow649soft.server.impl.http.request.HttpRequestLineParser.ParserStatus;
/**
 * Request parser used by server
 * @author Emanuele Lombardi
 *
 */
public class HttpRequestParser implements RequestParser {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    private Charset charset;
    HttpRequestLineParser lineParser;
    private HttpRequest result;
    private List<ByteBuffer> dataToRead;

    public HttpRequestParser() {
        charset = Charset.forName("UTF-8");
        this.lineParser = new HttpRequestLineParser();
        result = new HttpRequest();
        this.dataToRead = new CopyOnWriteArrayList<ByteBuffer>();
    }

    public boolean isRequestComplete() {
        return lineParser.getStatus() == ParserStatus.PARSED;
    }

    public void addData(ByteBuffer buffer) {
        this.dataToRead.add(buffer);
    }

    public synchronized void parseData() {
        if(!isRequestComplete()) {
            StringBuilder requestString = new StringBuilder();
            for (ByteBuffer buffer : dataToRead) {
                buffer.flip();
                String decodedString = charset.decode(buffer).toString();
                requestString.append(decodedString);
            }
            logger.info("current request is:" + requestString.toString());
            Scanner scanner = new Scanner(requestString.toString());
            while(scanner.hasNextLine()) {
                try {
                    lineParser.parse(scanner.nextLine());
                } catch (RequestParseException e) {
                    lineParser.destroyRequest();
                }
            }
            if(!isRequestComplete()) {
                lineParser.destroyRequest();
            } else if(result.getStatus() == Status.Dummy){
                result = new HttpRequest(lineParser.getRequest());
                result.setStatus(Status.Accepted);
            }
        }
    }

    public ServerRequest<HttpRequestMessage> getRequest() {
        return result;
    }

}
