package com.yp.easyrpcspringbootstarter.annotation;

import com.yp.easyrpcspringbootstarter.bootstrap.RpcConsumerBootstrap;
import com.yp.easyrpcspringbootstarter.bootstrap.RpcInitBootstrap;
import com.yp.easyrpcspringbootstarter.bootstrap.RpcProviderBootstrap;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yp
 * @date: 2024/3/16
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({RpcInitBootstrap.class, RpcProviderBootstrap.class, RpcConsumerBootstrap.class})
public @interface EnableRpc {

    boolean needServer() default true;
}
