package com.yp.rpc.loadbalancer;

import com.yp.rpc.model.ServiceMetaInfo;

import java.util.List;
import java.util.Map;

/**
 * 负载均衡器
 *
 * @author yp
 * @date: 2024/3/15
 */
public interface LoadBalancer {

    ServiceMetaInfo select(Map<String, Object> requestParams, List<ServiceMetaInfo> serviceMetaInfoList);
}
