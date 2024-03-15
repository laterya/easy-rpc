package com.yp.rpc.loadbalancer;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yp
 * @date: 2024/3/16
 */
public class LoadBalancerTest {

    final LoadBalancer loadBalancer = new ConsistentHashLoadBalancer();

    @Test
    public void select() {
        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("methodName", "apple");

    }
}