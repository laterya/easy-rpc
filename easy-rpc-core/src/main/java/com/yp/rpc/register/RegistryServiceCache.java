package com.yp.rpc.register;

import com.yp.rpc.model.ServiceMetaInfo;

import java.util.List;

/**
 * 注册中心服务本地缓存
 *
 * @author yp
 * @date: 2024/3/15
 */
public class RegistryServiceCache {
    /**
     * 服务缓存
     */
    List<ServiceMetaInfo> serviceCache;

    void writeCache(List<ServiceMetaInfo> newServiceMetaCache) {
        this.serviceCache = newServiceMetaCache;
    }

    List<ServiceMetaInfo> readCache() {
        return this.serviceCache;
    }

    void clearCache() {
        this.serviceCache = null;
    }
}
