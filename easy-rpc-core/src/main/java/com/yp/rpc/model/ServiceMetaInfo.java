package com.yp.rpc.model;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

/**
 * 服务元信息
 *
 * @author yp
 * date: 2024/3/14
 */
@Data
public class ServiceMetaInfo {

    private String serviceName;

    private String serviceVersion = "1.0";

    private String serviceAddress;

    private String serviceGroup = "default";

    private String serviceHost;

    private Integer servicePort;

    public String getServiceKey() {
        return String.format("%s:%s", serviceName, serviceVersion);
    }

    /**
     * 获取服务注册节点键名
     *
     * @return
     */
    public String getServiceNodeKey() {
        return String.format("%s/%s", getServiceKey(), serviceHost + ":" + servicePort);
    }

    public String getServiceAddress() {
        if (!StrUtil.contains(serviceHost, "http")) {
            return String.format("http://%s:%s", serviceHost, servicePort);
        }
        return String.format("%s:%s", serviceHost, servicePort);
    }
}
