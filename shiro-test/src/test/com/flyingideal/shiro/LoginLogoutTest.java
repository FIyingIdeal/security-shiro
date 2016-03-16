package com.flyingideal.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2016/3/5.
 */
public class LoginLogoutTest {

    @Test
    public void testHelloWorld() {
        //1.获取SecurityManager 工厂，此处使用Ini配置文件初始化SecurityManager
//        Factory<SecurityManager> factory =
//                new IniSecurityManagerFactory("file:G:\\a\\shiro.ini");
        //realm test
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory("file:G:\\a\\shiro-realm.ini");
        //2.得到SecurityManager实例，并绑定给SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        //3.得到Subject及创建用户名/身份验证的token
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");
        try {
            subject.login(token);
        } catch(AuthenticationException e) {
            System.out.println("用户验证失败！");
        }
        Assert.assertEquals(true, subject.isAuthenticated());
        subject.logout();
    }
}
