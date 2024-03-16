package com.yp.rpc.proxy;

import com.yp.rpc.RpcApplication;

import java.lang.reflect.Proxy;

/**
 * 动态代理类工厂
 *
 * @author yp
 * date: 2024/3/10
 */
public class ServiceProxyFactory {

    public static <T> T getProxy(Class<T> serviceClass) {
        if (RpcApplication.getRpcConfig().isMock()) {
            return getMockProxy(serviceClass);
        }
        return (T) Proxy.newProxyInstance(
                serviceClass.getClassLoader(),
                new Class[]{serviceClass},
                new ServiceProxy());
    }

    private static <T> T getMockProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(),
                new Class[]{serviceClass}, new MockServiceProxy());
    }
}
