package com.yp.rpc.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yp
 * date: 2024/3/10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RpcResponse implements Serializable {

    private static final long serialVersionUID = 4769182776046378656L;

    private Object data;

    private Class<?> dataType;

    private String message;

    private Exception exception;
}
