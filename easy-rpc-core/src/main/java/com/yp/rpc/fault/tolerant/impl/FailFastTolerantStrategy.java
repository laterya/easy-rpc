package com.yp.rpc.fault.tolerant.impl;

import com.yp.rpc.fault.tolerant.TolerantStrategy;
import com.yp.rpc.model.RpcResponse;

import java.util.Map;

/**
 * @author yp
 * date: 2024/3/16
 */
public class FailFastTolerantStrategy implements TolerantStrategy {
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        throw new RuntimeException("服务报错", e);
    }
}
