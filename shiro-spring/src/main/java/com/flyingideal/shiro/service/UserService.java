package com.flyingideal.shiro.service;

import com.flyingideal.shiro.entity.User;

/**
 * Created by Administrator on 2016/3/22.
 */
public interface UserService {

    public User createUser(User user);

    public User findByUsername(String username);
}
