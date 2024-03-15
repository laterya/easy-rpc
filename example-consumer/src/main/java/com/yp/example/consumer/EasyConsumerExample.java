package com.yp.example.consumer;

import com.yp.example.common.model.User;
import com.yp.example.common.service.UserService;
import com.yp.rpc.proxy.ServiceProxyFactory;

/**
 * @author yp
 * @date: 2024/3/10
 */
public class EasyConsumerExample {

    public static void main(String[] args) {
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("nihao");
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("newUser is null");
        }

        User user1 = new User();
        user1.setName("nihao");
        User newUser1 = userService.getUser(user1);
        if (newUser1 != null) {
            System.out.println(newUser1.getName());
        } else {
            System.out.println("newUser1 is null");
        }

        User user2 = new User();
        user2.setName("nihao");
        User newUser2 = userService.getUser(user2);
        if (newUser2 != null) {
            System.out.println(newUser2.getName());
        } else {
            System.out.println("newUser is null");
        }
    }
}
