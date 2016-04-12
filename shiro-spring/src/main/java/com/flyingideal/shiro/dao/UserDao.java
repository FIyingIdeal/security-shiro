package com.flyingideal.shiro.dao;

import com.flyingideal.shiro.entity.User;

/**
 * Created by Administrator on 2016/3/22.
 */
public interface UserDao {
    public User createUser(User user);

    public User findByUsername(String username);
}
