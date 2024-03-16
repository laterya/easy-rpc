package com.yp.rpc.fault.retry;

import com.yp.rpc.model.RpcResponse;

import java.util.concurrent.Callable;

/**
 * @author yp
 * date: 2024/3/16
 */
public interface RetryStrategy {
    RpcResponse doRetry(Callable<RpcResponse> callable) throws Exception;
}
