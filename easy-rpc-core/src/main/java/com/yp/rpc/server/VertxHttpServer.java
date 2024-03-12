package com.yp.rpc.server;

import io.vertx.core.Vertx;

/**
 * @author yp
 * @date: 2024/3/10
 */
public class VertxHttpServer implements HttpServer {
    @Override
    public void doStart(int port) {
        Vertx vertx = Vertx.vertx();
        // 创建 HTTP 服务器
        io.vertx.core.http.HttpServer server = vertx.createHttpServer();

        server.requestHandler(new HttpServerHandler());
        // 启动 HTTP 服务器并监听指定端口
        server.listen(port, httpServerAsyncResult -> {
            if (httpServerAsyncResult.succeeded()) {
                System.out.println("成功监听端口：" + port);
            } else {
                System.out.println("监听端口失败" + httpServerAsyncResult.cause());
            }
        });
    }
}
