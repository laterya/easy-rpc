package com.yp.rpc.fault.tolerant.impl;

import com.yp.rpc.fault.tolerant.TolerantStrategy;
import com.yp.rpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author yp
 * date: 2024/3/16
 */
@Slf4j
public class FailSafeTolerantStrategy implements TolerantStrategy {
    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        log.info("静默处理异常", e);
        return new RpcResponse();
    }
}
