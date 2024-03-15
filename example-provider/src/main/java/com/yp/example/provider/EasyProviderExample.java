package com.yp.example.provider;

import com.yp.example.common.service.UserService;
import com.yp.rpc.RpcApplication;
import com.yp.rpc.config.RegistryConfig;
import com.yp.rpc.config.RpcConfig;
import com.yp.rpc.model.ServiceMetaInfo;
import com.yp.rpc.register.LocalRegister;
import com.yp.rpc.register.RegisterFactory;
import com.yp.rpc.register.Registry;
import com.yp.rpc.server.VertxHttpServer;

/**
 * @author yp
 * @date: 2024/3/10
 */
public class EasyProviderExample {

    public static void main(String[] args) {
        RpcApplication.init();
        LocalRegister.register(UserService.class.getName(), UserServiceImpl.class);
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry instance = RegisterFactory.getInstance(registryConfig.getRegistry());

        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(UserService.class.getName());
        serviceMetaInfo.setServiceAddress(rpcConfig.getServerHost() + ":" + rpcConfig.getServerPort());
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());

        try {
            instance.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException("注册服务失败", e);
        }
        VertxHttpServer vertxHttpServer = new VertxHttpServer();
        vertxHttpServer.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
