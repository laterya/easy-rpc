package com.yp.rpc.config;

import lombok.Data;

/**
 * @author yp
 * date: 2024/3/14
 */
@Data
public class RegistryConfig {

    private String registry = "etcd";

    private String address = "http://localhost:2380";

    private String username;

    private String password;

    /**
     * 连接超时时间（毫秒）
     */
    private Long timeout = 10000L;
}
