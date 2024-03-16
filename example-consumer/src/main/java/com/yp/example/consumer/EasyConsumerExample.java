package com.yp.example.consumer;

import com.yp.example.common.model.User;
import com.yp.example.common.service.UserService;
import com.yp.rpc.bootstrap.ConsumerBootstrap;
import com.yp.rpc.proxy.ServiceProxyFactory;

/**
 * @author yp
 * @date: 2024/3/10
 */
public class EasyConsumerExample {

    public static void main(String[] args) {
        ConsumerBootstrap.init();

        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("nihao");
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("newUser is null");
        }
    }
}
