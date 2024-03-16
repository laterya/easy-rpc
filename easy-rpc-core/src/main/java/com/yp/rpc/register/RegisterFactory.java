package com.yp.rpc.register;

import com.yp.rpc.spi.SpiLoader;

/**
 * @author yp
 * date: 2024/3/14
 */
public class RegisterFactory {

    static {
        SpiLoader.load(Registry.class);
    }

    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();

    public static Registry getInstance(String key) {
        return SpiLoader.getInstance(Registry.class, key);
    }
}
