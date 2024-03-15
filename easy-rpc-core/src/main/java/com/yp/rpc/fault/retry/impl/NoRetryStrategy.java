package com.yp.rpc.fault.retry.impl;

import com.yp.rpc.fault.retry.RetryStrategy;
import com.yp.rpc.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * @author yp
 * @date: 2024/3/16
 */
public class NoRetryStrategy implements RetryStrategy {
    @Override
    public RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception {
        return callable.call();
    }
}
