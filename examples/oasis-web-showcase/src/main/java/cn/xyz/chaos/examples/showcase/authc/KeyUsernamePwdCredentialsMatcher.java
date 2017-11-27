package cn.xyz.chaos.examples.showcase.authc;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.credential.CredentialsMatcher;

import cn.xyz.chaos.examples.showcase.authc.entity.KeyUsernamePwdToken;

/**
 * KeyUsernamePwdCredentialsMatcher <br/>
 * KEY username password 证书匹配
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年6月27日 下午2:55:49
 * @author lcg
 */
public class KeyUsernamePwdCredentialsMatcher implements CredentialsMatcher {

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        KeyUsernamePwdToken t = (KeyUsernamePwdToken) token;
        if (t.isNeedNoMatch()) {
            return true;
        }
        String realPwd = info.getCredentials().toString();
        String pwd = String.valueOf((char[]) t.getCredentials());
        if (realPwd.equals(pwd)) {
            return true;
        }
        throw new IncorrectCredentialsException("证书错误（KEY/密码）与用户名不一致");
    }

}
