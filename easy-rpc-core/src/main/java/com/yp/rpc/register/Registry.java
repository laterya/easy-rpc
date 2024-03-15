package com.yp.rpc.register;

import com.yp.rpc.config.RegistryConfig;
import com.yp.rpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author yp
 * @date: 2024/3/14
 */
public interface Registry {

    void init(RegistryConfig registryConfig);

    /**
     * 注册服务
     *
     * @param serviceMetaInfo
     */
    void register(ServiceMetaInfo serviceMetaInfo) throws ExecutionException, InterruptedException;

    /**
     * 注销服务
     *
     * @param serviceMetaInfo
     */
    void unRegister(ServiceMetaInfo serviceMetaInfo) throws ExecutionException, InterruptedException;

    /**
     * 服务发现（获取某服务的所有节点，消费端）
     *
     * @param serviceKey
     * @return
     */
    List<ServiceMetaInfo> serviceDiscovery(String serviceKey);

    void destroy();
}
