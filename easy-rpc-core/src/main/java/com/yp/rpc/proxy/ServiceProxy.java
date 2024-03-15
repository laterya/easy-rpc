package com.yp.rpc.proxy;

import cn.hutool.core.collection.CollUtil;
import com.yp.rpc.RpcApplication;
import com.yp.rpc.config.RpcConfig;
import com.yp.rpc.constant.RpcConstant;
import com.yp.rpc.loadbalancer.LoadBalancer;
import com.yp.rpc.loadbalancer.LoadBalancerFactory;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            // 负载均衡
            LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(rpcConfig.getLoadBalancer());
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put("methodName", rpcRequest.getMethodName());
            ServiceMetaInfo selectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);

            // 发送 TCP 请求
            RpcResponse rpcResponse = VertxTcpClient.doRequest(rpcRequest, selectedServiceMetaInfo);
            return rpcResponse.getData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
