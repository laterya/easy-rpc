package com.yp.rpc.loadbalancer;

import com.yp.rpc.loadbalancer.impl.RoundRobinLoadBalancer;
import com.yp.rpc.spi.SpiLoader;

/**
 * @author yp
 * date: 2024/3/15
 */
public class LoadBalancerFactory {

    static {
        SpiLoader.load(LoadBalancer.class);
    }

    private static final LoadBalancer DEFAULT_LOAD_BALANCER = new RoundRobinLoadBalancer();

    public static LoadBalancer getInstance(String key) {
        return SpiLoader.getInstance(LoadBalancer.class, key);
    }
}
