package com.yp.rpc.loadbalancer;

/**
 * @author yp
 * @date: 2024/3/15
 */
public interface LoadBalancerKeys {

    String ROUND_ROBIN = "roundRobin";

    String RANDOM = "random";

    String CONSISTENT_HASH = "consistentHash";
}
