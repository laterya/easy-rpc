package com.yp.rpc.protocol;

import com.yp.rpc.model.RpcRequest;
import com.yp.rpc.model.RpcResponse;
import com.yp.rpc.protocol.model.ProtocolConstant;
import com.yp.rpc.protocol.model.ProtocolMessage;
import com.yp.rpc.protocol.model.ProtocolMessageSerializerEnum;
import com.yp.rpc.protocol.model.ProtocolMessageTypeEnum;
import com.yp.rpc.serializer.Serializer;
import com.yp.rpc.serializer.SerializerFactory;
import io.vertx.core.buffer.Buffer;

import java.io.IOException;

/**
 * 解码器
 *
 * @author yp
 * date: 2024/3/15
 */
public class ProtocolMessageDecoder {

    public static ProtocolMessage<?> decode(Buffer buffer) throws IOException {
        ProtocolMessage.Header header = new ProtocolMessage.Header();
        byte magic = buffer.getByte(0);
        // 校验魔数
        if (magic != ProtocolConstant.PROTOCOL_MAGIC) {
            throw new RuntimeException("消息 magic 非法");
        }
        header.setMagic(magic);
        header.setVersion(buffer.getByte(1));
        header.setSerializer(buffer.getByte(2));
        header.setType(buffer.getByte(3));
        header.setStatus(buffer.getByte(4));
        header.setRequestId(buffer.getByte(5));
        header.setBodyLength(buffer.getInt(13));
        // 粘包问题，只读指定长度的数据进行解决
        byte[] bodyBytes = buffer.getBytes(17, 17 + header.getBodyLength());
        // 解析消息体
        ProtocolMessageSerializerEnum serializerEnum = ProtocolMessageSerializerEnum.getEnumByKey(header.getSerializer());
        if (serializerEnum == null) {
            throw new RuntimeException("序列化消息的协议不存在");
        }
        ProtocolMessageTypeEnum messageTypeEnum = ProtocolMessageTypeEnum.getEnumByValue(header.getType());
        if (messageTypeEnum == null) {
            throw new RuntimeException("序列化消息的类型不存在");
        }

        Serializer serializer = SerializerFactory.getInstance(serializerEnum.getValue());
        switch (messageTypeEnum) {
            case REQUEST:
                RpcRequest rpcRequest = serializer.deserialize(bodyBytes, RpcRequest.class);
                return new ProtocolMessage<>(header, rpcRequest);
            case RESPONSE:
                RpcResponse rpcResponse = serializer.deserialize(bodyBytes, RpcResponse.class);
                return new ProtocolMessage<>(header, rpcResponse);
            case HEART_BEAT:
            case OTHERS:
            default:
                throw new RuntimeException("暂不支持该消息类型");
        }

    }
}
