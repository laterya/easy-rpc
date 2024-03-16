package com.yp.rpc.fault.tolerant;

import com.yp.rpc.model.RpcResponse;

import java.util.Map;

/**
 * @author yp
 * date: 2024/3/16
 */
public interface TolerantStrategy {

    RpcResponse doTolerant(Map<String, Object> context, Exception e);
}
