package com.yp.rpc.fault.retry;

import com.yp.rpc.fault.retry.impl.NoRetryStrategy;
import com.yp.rpc.spi.SpiLoader;

/**
 * @author yp
 * @date: 2024/3/16
 */
public class RetryStrategyFactory {

    static {
        SpiLoader.load(RetryStrategy.class);
    }

    private static final RetryStrategy DEFAULT_RETRY_STRATEGY = new NoRetryStrategy();

    public static RetryStrategy getInstance(String key) {
        return SpiLoader.getInstance(RetryStrategy.class, key);
    }
}
