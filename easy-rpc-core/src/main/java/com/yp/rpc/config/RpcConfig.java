package com.yp.rpc.config;

import com.yp.rpc.serializer.SerializerKeys;
import lombok.Data;

/**
 * @author yp
 * @date: 2024/3/12
 */
@Data
public class RpcConfig {

    private String name = "easy-rpc";

    private String version = "1.0";

    private String serverHost = "localhost";

    private Integer serverPort = 8080;

    private boolean mock = false;

    private String serializer = SerializerKeys.JDK;
}
