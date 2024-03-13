package com.yp.example.common.service;

import com.yp.example.common.model.User;

/**
 * @author yp
 * @date: 2024/3/10
 */
public interface UserService {

    public User getUser(User user);

    default int getNumber() {
        return 1024;
    }
}
