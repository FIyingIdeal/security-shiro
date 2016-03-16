package com.flyingideal.shiro.jdbcrealm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * Created by Administrator on 2016/3/6.
 */
public class JDBCRealmTest {

    @Test
    public void jdbcRealTest() {
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("file:G:\\a\\shiro-jdbc-realm.ini");
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println("用户验证错误");
        }
        subject.logout();
    }
}
