package com.flyingideal.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by Administrator on 2016/3/5.
 */
public class MyRealm1 implements Realm {
    @Override
    public String getName() {
        return "myrealm1";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        //仅支持UsernamePasswordToken类型的Token
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取用户名
        String username = (String)authenticationToken.getPrincipal();
        //获取密码
        String password = new String((char[])authenticationToken.getCredentials());
        System.out.println("MyRealm1 : " + username + "  " + password);
        if (!"zhang".equals(username)) {
            throw new UnknownAccountException();
        }
        if (!"123".equals(password)) {
            throw new IncorrectCredentialsException();
        }
        //如果认证验证成功，返回一个AuthenticationInfo实现
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
