package com.flyingideal.shiro.realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created by Administrator on 2016/3/5.
 */
public class MyRealm2 implements Realm {
    @Override
    public String getName() {
        return MyRealm2.class.getName();
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("myrealm2 invoke");
        String username = (String)authenticationToken.getPrincipal();
        String password = new String((char[])authenticationToken.getPrincipal());
        if (!"wang".equals(username)) {
            throw new UnknownAccountException();
        }

        if (!"1234".equals(password)) {
            throw new IncorrectCredentialsException();
        }
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
