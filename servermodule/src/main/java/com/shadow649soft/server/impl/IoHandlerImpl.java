package com.shadow649soft.server.impl;

import static java.nio.ByteBuffer.allocate;
import static com.shadow649soft.server.api.request.ServerRequest.Status.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.shadow649soft.server.api.application.ApplicationContext;
import com.shadow649soft.server.api.response.ResponseBufferStorage;
import com.shadow649soft.server.api.server.IOHandler;
import com.shadow649soft.server.api.server.Server;
import com.shadow649soft.server.api.server.Server.Status;
/**
 * 
 * @author Emanuele Lombardi
 *
 */
public class IoHandlerImpl implements IOHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    private int port;
    private ServerSocketChannel server;
    private Selector selector;
    private Server srv;
    private ApplicationContext ctx;
    private ExecutorService workerExecutor;


    public void init(Server srv, ApplicationContext ctx) {
        try {
            this.srv = srv;
            this.port = srv.getServerConfiguration().getPort();
            server = ServerSocketChannel.open();
            server.configureBlocking(false);
            server.socket().bind(new InetSocketAddress(port));
            workerExecutor = Executors.newFixedThreadPool(srv.getServerConfiguration().getThreadNumber());
            srv.setStatus(Status.INITIALIZED);
            selector = Selector.open();
            this.ctx = ctx;
        } catch (IOException e) {
            throw new RuntimeException("Impossible to start te server ",e);
        }
    }

    public void run() {
        start();
    }

    public void start() {
        try {
            server.register( selector, SelectionKey.OP_ACCEPT );
        } catch (ClosedChannelException e) {
            throw new RuntimeException( "Something wrong happened while listening for connections", e );        }
        logger.info("Server is listening on port " + port);
        srv.setStatus(Status.RUNNING);
        while ( Status.RUNNING == srv.getStatus() ) {
            try
            {
                selector.select();
            } catch ( Throwable t )
            {
                throw new RuntimeException( "Something wrong happened while handling connections", t );
            }
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while ( keys.hasNext() )
            {
                SelectionKey key = keys.next();
                keys.remove();
                if (!key.isValid()) {
                    continue;
                }
                try
                {
                    if ( key.isAcceptable() )
                    {
                        accept( key );
                    }
                    else if ( key.isReadable() )
                    {
                        read( key);
                    }
                    else if ( key.isWritable() )
                    {
                        write( key );
                    }
                }
                catch ( IOException e )
                {
                    logger.error( "Unexpected error", e );

                    key.cancel();
                }
            }
        }
        logger.info( "Server is shutting down" );

        try
        {
            if ( selector != null && selector.isOpen() )
            {
                selector.close();
            }
        }             catch ( IOException e )
        {
            throw new RuntimeException( "An error occurred while closing selector: %s" + e.getMessage() );
        }
        finally {
            try {
                server.close();
                logger.info("Shutdown complete");
            } catch (IOException e) {
                throw new RuntimeException( "An error occurred while closing server: %s" + e.getMessage() );
            }
        }
        
        
    }

    public void close() {
        selector.wakeup();
        workerExecutor.shutdown();
    }

    private void accept( SelectionKey key ) throws IOException
    {
        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
        SocketChannel socketChannel = serverChannel.accept();

        if ( socketChannel == null )
        {
            return;
        }

        socketChannel.configureBlocking( false );

        Socket socket = socketChannel.socket();

        logger.info( "Accepting new request from {} {}", socket.getInetAddress().getHostAddress(),key.hashCode() );
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private void read( SelectionKey key ) throws IOException
    {
        //ADD REQUEST PARSER WITH THE SELECTED KEY
        logger.info("Read key hashcode " + key.hashCode() );
        SocketChannel socketChannel = (SocketChannel) key.channel();
        ByteBuffer data = allocate( 100 );
        int count = 1;
        count = socketChannel.read(data);
        logger.info("Readed " + count + " byte from" + key.hashCode());
        if (count == -1) {
            // Remote entity shut the socket down cleanly. Do the
            // same from our end and cancel the channel.
            key.channel().close();
            key.cancel();
            logger.info("removed key "+ key.hashCode());
            return;
        }
        if(count > 0 && (ctx.getRequestParser(key).getRequest().getStatus() == Dummy || ctx.getRequestParser(key).getRequest().getStatus() == Accepted)) {
            Worker worker = new Worker(key, ctx);
            worker.notifyDataReceived(data);
            workerExecutor.execute(worker);
        }

    }

    private void write(SelectionKey key) throws SocketException, ClosedChannelException {
        Queue<ByteBuffer> responseBuffers = ResponseBufferStorage.getInsance().get(key);
        if(responseBuffers != null) {
            SocketChannel writeChannel = (SocketChannel) key.channel();
            ByteBuffer current = responseBuffers.poll();
            if(current != ResponseBufferStorage.EOM) {
                try {
                    writeChannel.write(current);
                } catch (IOException e) {
                    logger.error( "Unexpected error while writing on the connection", e );
                }
            } else {
                //END OF DATA TO WRITE
                Socket socket = writeChannel.socket();
                logger.info( "Request with {} served.", socket.getInetAddress().getHostAddress() );
                ctx.removeData(key);
                if ( socket.getKeepAlive() )
                {
                    logger.info( "Connection with {} will kept alive", socket.getInetAddress().getHostAddress() );
                    writeChannel.register(selector, SelectionKey.OP_READ);
                } else {
                    try {
                        key.channel().close();
                    } catch (IOException e) {
                        logger.error( "Unexpected error while closing the connection", e );
                    }
                    key.cancel();
                    logger.info("writed data closed connection, removed key {}", key.hashCode());
                }
            }
        } else {
            logger.warn("Key in write mode but no data to write");
        }
    }



    public InetAddress getLocalAddress() {
        return server.socket().getInetAddress();
    }

    public int getLocalPort() {
        return port;
    }

}
