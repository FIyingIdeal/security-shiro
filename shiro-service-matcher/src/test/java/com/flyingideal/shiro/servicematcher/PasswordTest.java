package com.flyingideal.shiro.servicematcher;

import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.junit.Test;

/**
 * Created by Administrator on 2016/3/16.
 */
public class PasswordTest extends BaseTest {

    @Test(expected = ExcessiveAttemptsException.class)
    public void testRetryLimitHashedCredentialsMatcherWithMyRealm() {
        for (int i = 1; i <=5; i++) {
            try {
                login("classpath:shiro-retryLimitHashedCredentialsMatcher.ini", "zhang", "234");
            } catch (Exception e) {}
        }
        login("classpath:shiro-retryLimitHashedCredentialsMatcher.ini", "liu", "234");
    }
}
