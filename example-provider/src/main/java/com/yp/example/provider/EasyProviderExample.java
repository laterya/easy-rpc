package com.yp.example.provider;

import com.yp.example.common.service.UserService;
import com.yp.rpc.register.LocalRegister;
import com.yp.rpc.server.VertxHttpServer;

/**
 * @author yp
 * @date: 2024/3/10
 */
public class EasyProviderExample {

    public static void main(String[] args) {
        LocalRegister.register(UserService.class.getName(), UserServiceImpl.class);

        VertxHttpServer vertxHttpServer = new VertxHttpServer();
        vertxHttpServer.doStart(8080);
    }
}
