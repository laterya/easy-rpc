package com.yp.rpc.config;

import com.yp.rpc.serializer.SerializerKeys;
import lombok.Data;

/**
 * RPC 的相关配置
 *
 * @author yp
 * @date: 2024/3/12
 */
@Data
public class RpcConfig {

    // todo 自动识别服务方的地址并注册

    private String name = "easy-rpc";

    private String version = "1.0";

    private String serverHost = "localhost";

    private Integer serverPort = 8080;

    private boolean mock = false;

    private String serializer = SerializerKeys.JDK;

    private RegistryConfig registryConfig = new RegistryConfig();
}
