package com.yp.rpc.serializer;

import com.yp.rpc.serializer.impl.JdkSerializer;
import com.yp.rpc.spi.SpiLoader;

/**
 * @author yp
 * @date: 2024/3/13
 */
public class SerializerFactory {

    static {
        SpiLoader.load(Serializer.class);
    }

    private static final Serializer DEFAULT_SERIALIZER = new JdkSerializer();

    public static Serializer getInstance(String key) {
        return SpiLoader.getInstance(Serializer.class, key);
    }
}
