package com.flyingideal.shiro.permission;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Administrator on 2016/3/7.
 */
public class PermissionTest {
    private void login(String configFile, String username, String password) {
        Factory<SecurityManager> factory =
                new IniSecurityManagerFactory(configFile);
        SecurityManager manager = factory.getInstance();
        SecurityUtils.setSecurityManager(manager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
    }

    private Subject subject() {
        return SecurityUtils.getSubject();
    }

    @Test
    public void testHasRole() {
        login("file:G:\\a\\shiro-permission.ini", "zhang", "123");

        Subject subject = SecurityUtils.getSubject();
        System.out.println(subject.hasRole("role1"));
        System.out.println(subject.hasAllRoles(Arrays.asList("role1", "role2")));
    }

    @Test
    public void testIsPermitted() {
        login("file:G:\\a\\shiro-permission.ini", "zhang", "123");
        //System.out.println(subject().isPermitted("user:create"));
        boolean[] bool = subject().isPermitted("user:create","user:update");
//        boolean bool = subject().isPermitted("user:create,update");
//        System.out.println(bool);
        for (boolean b : bool) {
            System.out.println(b);
        }
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission() {
        login("file:G:\\a\\shiro-permission.ini", "zhang", "123");
        subject().checkPermissions("user:create,update,view");
    }

}
