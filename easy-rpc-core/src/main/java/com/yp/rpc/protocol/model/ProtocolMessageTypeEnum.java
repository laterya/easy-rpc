package com.yp.rpc.protocol.model;

import lombok.Getter;

/**
 * 协议消息状态枚举
 *
 * @author yp
 * date: 2024/3/15
 */
@Getter
public enum ProtocolMessageTypeEnum {

    REQUEST(0),
    RESPONSE(1),
    HEART_BEAT(2),
    OTHERS(3);

    private final int key;

    ProtocolMessageTypeEnum(int key) {
        this.key = key;
    }

    public static ProtocolMessageTypeEnum getEnumByValue(int key) {
        for (ProtocolMessageTypeEnum protocolMessageStatusEnum : ProtocolMessageTypeEnum.values()) {
            if (protocolMessageStatusEnum.key == key) {
                return protocolMessageStatusEnum;
            }
        }
        return null;
    }
}
