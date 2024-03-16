package com.yp.rpc;

import com.yp.rpc.config.RegistryConfig;
import com.yp.rpc.config.RpcConfig;
import com.yp.rpc.constant.RpcConstant;
import com.yp.rpc.register.RegisterFactory;
import com.yp.rpc.register.Registry;
import com.yp.rpc.serializer.SerializerFactory;
import com.yp.rpc.utils.ConfigUtils;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yp
 * date: 2024/3/12
 */
@Slf4j
public class RpcApplication {

    private static volatile RpcConfig rpcConfig;

    public static void init(RpcConfig newRpcConfig) {
        rpcConfig = newRpcConfig;
        log.info("rpc init, config = {}", newRpcConfig.toString());
        // 注册中心初始化
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegisterFactory.getInstance(registryConfig.getRegistry());
        registry.init(registryConfig);
        log.info("registry init, config = {}", registryConfig);

        // 创建并注册 Shutdown Hook， JVM 退出时执行操作
        Runtime.getRuntime().addShutdownHook(new Thread(registry::destroy));
    }

    public static void init() {
        RpcConfig newRpcConfig;
        try {
            newRpcConfig = ConfigUtils.loadConfig(RpcConfig.class, RpcConstant.DEFAULT_CONFIG_PREFIX);
        } catch (Exception e) {
            newRpcConfig = new RpcConfig();
        }
        init(newRpcConfig);
    }

    public static RpcConfig getRpcConfig() {
        if (rpcConfig == null) {
            synchronized (RpcApplication.class) {
                if (rpcConfig == null) {
                    init();
                }
            }
        }
        return rpcConfig;
    }

    public static void main(String[] args) {
        SerializerFactory.getInstance("jdk");
    }
}
