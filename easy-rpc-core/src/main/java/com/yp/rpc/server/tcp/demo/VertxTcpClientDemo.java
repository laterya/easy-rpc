package com.yp.rpc.server.tcp.demo;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetSocket;

/**
 * @author yp
 * @date: 2024/3/15
 */
public class VertxTcpClientDemo {

    public void start() {
        Vertx vertx = Vertx.vertx();
        vertx.createNetClient().connect(8090, "localhost", netSocketAsyncResult -> {
            if (netSocketAsyncResult.succeeded()) {
                System.out.println("Connected to Tcp server");
                NetSocket netSocket = netSocketAsyncResult.result();
                for (int i = 0; i < 1000; i++) {
                    netSocket.write("Hello, server");
                }
                netSocket.handler(buffer -> {
                    System.out.println("received response from server:" + buffer.toString());
                });
            } else {
                System.out.println("Failed to connect to Tcp server");
            }
        });
    }

    public static void main(String[] args) {
        new VertxTcpClientDemo().start();
    }
}
