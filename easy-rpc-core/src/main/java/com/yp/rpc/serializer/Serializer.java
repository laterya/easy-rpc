package com.yp.rpc.serializer;

import java.io.IOException;

/**
 * @author yp
 * date: 2024/3/10
 */
public interface Serializer {

    <T> byte[] serialize(T object) throws IOException;

    <T> T deserialize(byte[] bytes, Class<T> type) throws IOException;
}
