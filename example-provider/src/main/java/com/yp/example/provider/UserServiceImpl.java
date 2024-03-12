package com.yp.example.provider;

import com.yp.example.common.model.User;
import com.yp.example.common.service.UserService;

/**
 * @author yp
 * @date: 2024/3/10
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("服务提供者输出：用户名是" + user.getName());
        return user;
    }
}
