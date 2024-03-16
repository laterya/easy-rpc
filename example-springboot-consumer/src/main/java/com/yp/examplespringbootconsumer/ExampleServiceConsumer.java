package com.yp.examplespringbootconsumer;

import com.yp.easyrpcspringbootstarter.annotation.RpcReference;
import com.yp.example.common.model.User;
import com.yp.example.common.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author yp
 * @date: 2024/3/16
 */
@Service
public class ExampleServiceConsumer {

    @RpcReference
    private UserService userService;


    public void test() {
        User user = new User();
        user.setName("laterya");
        User user1 = userService.getUser(user);
        System.out.println(user1.getName());
    }
}
