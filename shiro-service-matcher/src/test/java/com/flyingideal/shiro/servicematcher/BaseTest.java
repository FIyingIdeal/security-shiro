package com.flyingideal.shiro.servicematcher;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;

/**
 * Created by Administrator on 2016/3/16.
 */
public class BaseTest {

    @After
    public void tearDown() throws Exception {
        ThreadContext.unbindSubject();
    }

    protected void login(String configFile, String username, String password) {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFile);
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
    }

    public Subject subject() {
        return SecurityUtils.getSubject();
    }
}
