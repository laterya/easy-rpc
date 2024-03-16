package com.yp.examplespringbootprovider;

import com.yp.easyrpcspringbootstarter.annotation.RpcService;
import com.yp.example.common.model.User;
import com.yp.example.common.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author yp
 * @date: 2024/3/16
 */
@Service
@RpcService
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        User user1 = new User();
        user1.setName("来自SpringBoot-Provider的实现" + user.getName());
        return user1;
    }
}
