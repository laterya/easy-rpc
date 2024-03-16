package com.yp.rpc.protocol.model;

import cn.hutool.core.util.IdUtil;
import com.yp.rpc.constant.RpcConstant;
import com.yp.rpc.model.RpcRequest;
import com.yp.rpc.protocol.ProtocolMessageDecoder;
import com.yp.rpc.protocol.ProtocolMessageEncoder;
import io.vertx.core.buffer.Buffer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author yp
 * date: 2024/3/15
 */
public class ProtocolMessageTest {

    @Test
    public void testEncodeAndDecode() throws IOException {
        ProtocolMessage<RpcRequest> protocolMessage = new ProtocolMessage<>();
        ProtocolMessage.Header header = new ProtocolMessage.Header();
        header.setMagic(ProtocolConstant.PROTOCOL_MAGIC);
        header.setVersion(ProtocolConstant.PROTOCOL_VERSION);
        header.setSerializer((byte) ProtocolMessageSerializerEnum.JDK.getKey());
        header.setType((byte) ProtocolMessageTypeEnum.REQUEST.getKey());
        header.setStatus((byte) ProtocolMessageStatusEnum.OK.getValue());
        header.setRequestId(IdUtil.getSnowflakeNextId());
        header.setBodyLength(0);

        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setServiceName("myService");
        rpcRequest.setMethodName("myMethod");
        rpcRequest.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
        rpcRequest.setParameterTypes(new Class[]{String.class});
        rpcRequest.setArgs(new Object[]{"aaa", "bbb"});
        protocolMessage.setHeader(header);
        protocolMessage.setBody(rpcRequest);

        Buffer encode = ProtocolMessageEncoder.encode(protocolMessage);
        ProtocolMessage<?> decode = ProtocolMessageDecoder.decode(encode);
        Assert.assertNotNull(decode);
        System.out.println(decode);
    }

}