package com.yp.rpc.annotation;

import com.yp.rpc.bootstrap.RpcConsumerBootstrap;
import com.yp.rpc.bootstrap.RpcInitBootstrap;
import com.yp.rpc.bootstrap.RpcProviderBootstrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yp
 * date: 2024/3/16
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsumerBootstrap.class})
public @interface EnableRpc {

    boolean needServer() default true;
}
