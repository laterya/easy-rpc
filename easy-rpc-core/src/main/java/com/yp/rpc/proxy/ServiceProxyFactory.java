package com.yp.rpc.proxy;

import java.lang.reflect.Proxy;

/**
 * 动态代理类工厂
 *
 * @author yp
 * @date: 2024/3/10
 */
public class ServiceProxyFactory {

    public static <T> T getProxy(Class<T> serviceClass) {
        return (T) Proxy.newProxyInstance(serviceClass.getClassLoader(),
                new Class[]{serviceClass}, new ServiceProxy());
    }
}
