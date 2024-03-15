package com.yp.rpc.proxy;

import cn.hutool.core.collection.CollUtil;
import com.yp.rpc.RpcApplication;
import com.yp.rpc.config.RpcConfig;
import com.yp.rpc.constant.RpcConstant;
import com.yp.rpc.model.RpcRequest;
import com.yp.rpc.model.RpcResponse;
import com.yp.rpc.model.ServiceMetaInfo;
import com.yp.rpc.register.RegisterFactory;
import com.yp.rpc.register.Registry;
import com.yp.rpc.serializer.Serializer;
import com.yp.rpc.serializer.SerializerFactory;
import com.yp.rpc.server.tcp.VertxTcpClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 服务代理（JDK 动态代理）
 *
 * @author yp
 * @date: 2024/3/10
 */
public class ServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        Serializer serializer = SerializerFactory.getInstance(RpcApplication.getRpcConfig().getSerializer());

        // 发请求
        String serviceName = method.getDeclaringClass().getName();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName("getUser")
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 从注册中心获取服务信息
            RpcConfig rpcConfig = RpcApplication.getRpcConfig();
            Registry registry = RegisterFactory.getInstance(rpcConfig.getRegistryConfig().getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceMetaInfo.getServiceKey());
            if (CollUtil.isEmpty(serviceMetaInfoList)) {
                throw new RuntimeException("暂无服务地址");
            }
            // todo 设计算法负载均衡
            ServiceMetaInfo selectedServiceMetaInfo = serviceMetaInfoList.get(0);

            // 发送 TCP 请求
            RpcResponse rpcResponse = VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo);
            return rpcResponse.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
