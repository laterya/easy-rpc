package com.yp.rpc.serializer.impl;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.yp.rpc.model.RpcRequest;
import com.yp.rpc.model.RpcResponse;
import com.yp.rpc.serializer.Serializer;

import java.io.IOException;

/**
 * @author yp
 * @date: 2024/3/13
 */
public class JsonSerializer implements Serializer {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Override
    public <T> byte[] serialize(T object) throws IOException {
        return OBJECT_MAPPER.writeValueAsBytes(object);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> type) throws IOException {
        T obj = OBJECT_MAPPER.readValue(bytes, type);
        if (obj instanceof RpcRequest) {
            return handleRequest((RpcRequest) obj, type);
        }
        if (obj instanceof RpcResponse) {
            return handleResponse((RpcResponse) obj, type);
        }
        return obj;
    }

    private <T> T handleResponse(RpcResponse obj, Class<T> type) throws IOException {
        byte[] bytes = OBJECT_MAPPER.writeValueAsBytes(obj.getData());
        obj.setData(OBJECT_MAPPER.readValue(bytes, obj.getDataType()));
        return type.cast(obj);
    }

    private <T> T handleRequest(RpcRequest obj, Class<T> type) throws IOException {
        Class<?>[] parameterTypes = obj.getParameterTypes();
        Object[] args = obj.getArgs();

        for (int i = 0; i < parameterTypes.length; i++) {
            Class<?> parameterType = parameterTypes[i];
            if (!parameterType.isAssignableFrom(args[i].getClass())) {
                byte[] argBytes = OBJECT_MAPPER.writeValueAsBytes(args[i]);
                args[i] = OBJECT_MAPPER.readValue(argBytes, parameterType);
            }
        }
        return type.cast(obj);
    }
}
