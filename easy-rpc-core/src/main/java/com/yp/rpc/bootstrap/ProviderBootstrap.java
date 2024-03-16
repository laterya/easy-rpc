package com.yp.rpc.bootstrap;

import com.yp.rpc.RpcApplication;
import com.yp.rpc.config.RegistryConfig;
import com.yp.rpc.config.RpcConfig;
import com.yp.rpc.model.ServiceMetaInfo;
import com.yp.rpc.model.ServiceRegisterInfo;
import com.yp.rpc.register.LocalRegister;
import com.yp.rpc.register.RegisterFactory;
import com.yp.rpc.register.Registry;
import com.yp.rpc.server.tcp.VertxTcpServer;

import java.util.List;

/**
 * @author yp
 * date: 2024/3/16
 */
public class ProviderBootstrap {

    public static void init(List<ServiceRegisterInfo<?>> serviceRegisterInfoList) {
        RpcApplication.init();
        final RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        for (ServiceRegisterInfo<?> serviceRegisterInfo : serviceRegisterInfoList) {
            String serviceName = serviceRegisterInfo.getServiceName();
            LocalRegister.register(serviceName, serviceRegisterInfo.getImplClass());

            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegisterFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
            try {
                registry.register(serviceMetaInfo);
            } catch (Exception e) {
                throw new RuntimeException(serviceName + "服务注册失败", e);
            }
        }
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(rpcConfig.getServerPort());
    }
}
