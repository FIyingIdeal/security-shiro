package com.flyingideal.shiro.service;

import com.flyingideal.shiro.dao.UserDao;
import com.flyingideal.shiro.entity.User;

/**
 * Created by Administrator on 2016/3/22.
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private PassowrdHelper passowrdHelper;

    public void setPassowrdHelper(PassowrdHelper passowrdHelper) {
        this.passowrdHelper = passowrdHelper;
    }

    @Override
    public User createUser(User user) {
        passowrdHelper.encryptPassword(user);
        return userDao.createUser(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }
}
