package com.yp.rpc.server.tcp;

import cn.hutool.core.util.IdUtil;
import com.yp.rpc.RpcApplication;
import com.yp.rpc.model.RpcRequest;
import com.yp.rpc.model.RpcResponse;
import com.yp.rpc.model.ServiceMetaInfo;
import com.yp.rpc.protocol.ProtocolMessageDecoder;
import com.yp.rpc.protocol.ProtocolMessageEncoder;
import com.yp.rpc.protocol.model.ProtocolConstant;
import com.yp.rpc.protocol.model.ProtocolMessage;
import com.yp.rpc.protocol.model.ProtocolMessageSerializerEnum;
import com.yp.rpc.protocol.model.ProtocolMessageTypeEnum;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @author yp
 * @date: 2024/3/15
 */
public class VertxTcpClient {

    public static RpcResponse doRequest(RpcRequest rpcRequest, ServiceMetaInfo serviceMetaInfo) throws ExecutionException, InterruptedException {
        // 发送 TCP 请求
        Vertx vertx = Vertx.vertx();
        NetClient netClient = vertx.createNetClient();
        CompletableFuture<RpcResponse> responseCompletableFuture = new CompletableFuture<>();
        netClient.connect(serviceMetaInfo.getServicePort(), serviceMetaInfo.getServiceHost(),
                result -> {
                    if (!result.succeeded()) {
                        System.out.println("Failed to connect to TCP server");
                        return;
                    }
                    NetSocket netSocket = result.result();
                    ProtocolMessage<RpcRequest> protocolMessage = new ProtocolMessage<>();
                    ProtocolMessage.Header header = new ProtocolMessage.Header();
                    header.setMagic(ProtocolConstant.PROTOCOL_MAGIC);
                    header.setVersion(ProtocolConstant.PROTOCOL_VERSION);
                    header.setSerializer((byte) ProtocolMessageSerializerEnum.getEnumByValue(RpcApplication.getRpcConfig().getSerializer()).getKey());
                    header.setType((byte) ProtocolMessageTypeEnum.REQUEST.getKey());
                    header.setRequestId(IdUtil.getSnowflakeNextId());

                    protocolMessage.setHeader(header);
                    protocolMessage.setBody(rpcRequest);
                    // 编码请求
                    try {
                        Buffer encode = ProtocolMessageEncoder.encode(protocolMessage);
                        netSocket.write(encode);
                    } catch (Exception e) {
                        throw new RuntimeException("协议消息编码错误");
                    }
                    // 接收响应
                    TcpBufferHandlerWrapper bufferHandlerWrapper = new TcpBufferHandlerWrapper(
                            buffer -> {
                                try {
                                    ProtocolMessage<RpcResponse> rpcResponseProtocolMessage = (ProtocolMessage<RpcResponse>) ProtocolMessageDecoder.decode(buffer);
                                    responseCompletableFuture.complete(rpcResponseProtocolMessage.getBody());
                                } catch (Exception e) {
                                    throw new RuntimeException("协议消息解码错误");
                                }
                            }
                    );
                    netSocket.handler(bufferHandlerWrapper);
                });
        RpcResponse rpcResponse = responseCompletableFuture.get();
        netClient.close();
        return rpcResponse;
    }
}
