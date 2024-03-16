package com.yp.rpc.fault.tolerant;

import com.yp.rpc.fault.tolerant.impl.FailFastTolerantStrategy;
import com.yp.rpc.spi.SpiLoader;

/**
 * @author yp
 * @date: 2024/3/16
 */
public class TolerantStrategyFactory {

    static {
        SpiLoader.load(TolerantStrategy.class);
    }

    private static final TolerantStrategy DEFAULT_TOLERANT_STRATEGY =
            new FailFastTolerantStrategy();

    public static TolerantStrategy getInstance(String key) {
        return SpiLoader.getInstance(TolerantStrategy.class, key);
    }
}
