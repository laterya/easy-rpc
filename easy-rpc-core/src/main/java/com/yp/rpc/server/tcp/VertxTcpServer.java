package com.yp.rpc.server.tcp;

import com.yp.rpc.server.http.HttpServer;
import io.vertx.core.Vertx;
import io.vertx.core.net.NetServer;

/**
 * @author yp
 * date: 2024/3/15
 */
public class VertxTcpServer implements HttpServer {

    public static void main(String[] args) {
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(8090);
    }

    @Override
    public void doStart(int port) {
        Vertx vertx = Vertx.vertx();

        NetServer netServer = vertx.createNetServer();

        netServer.connectHandler(new TcpServerHandler());
        netServer.listen(port, netServerAsyncResult -> {
            if (netServerAsyncResult.succeeded()) {
                System.out.println("TCP server started on port: " + port);
            } else {
                System.out.println("Failed to start TCP server: " + netServerAsyncResult.cause());
            }
        });
    }

    private byte[] handleRequest(byte[] requestData) {
        return "Hello, you create tcp connection".getBytes();
    }
}
