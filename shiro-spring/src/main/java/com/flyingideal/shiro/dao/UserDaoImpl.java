package com.flyingideal.shiro.dao;

import com.flyingideal.shiro.entity.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Administrator on 2016/3/22.
 */
public class UserDaoImpl extends JdbcDaoSupport implements UserDao {
    @Override
    public User createUser(final User user) {
        final String sql = "insert into sys_users(username, password, salt, locked) values (?,?,?,?)";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        getJdbcTemplate().update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement psst = connection.prepareStatement(sql, new String[]{"id"});
                psst.setString(1, user.getUsername());
                psst.setString(2, user.getPassword());
                psst.setString(3, user.getSalt());
                psst.setBoolean(4, user.getLocked());
                return psst;
            }
        }, keyHolder);
        user.setId(keyHolder.getKey().longValue());
        return user;
    }

    @Override
    public User findByUsername(String username) {
        String sql = "select id, username, password, salt, locked from sys_users where username = ?";
        List<User> userList = getJdbcTemplate().query(sql, new BeanPropertyRowMapper<User>(User.class), username);
        if (userList.size() == 0) {
            return null;
        }
        return userList.get(0);
    }
}
