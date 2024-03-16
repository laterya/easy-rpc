package com.yp.rpc.register;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author yp
 * date: 2024/3/10
 */
public class LocalRegister {

    private static final Map<String, Class<?>> map = new ConcurrentHashMap<>();

    public static void register(String serviceName, Class<?> implClass) {
        map.put(serviceName, implClass);
    }

    public static Class<?> get(String serviceName) {
        return map.get(serviceName);
    }

    public static void remove(String serviceName) {
        map.remove(serviceName);
    }
}
