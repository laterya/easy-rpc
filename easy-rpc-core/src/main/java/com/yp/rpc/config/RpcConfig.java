package com.yp.rpc.config;

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
}
