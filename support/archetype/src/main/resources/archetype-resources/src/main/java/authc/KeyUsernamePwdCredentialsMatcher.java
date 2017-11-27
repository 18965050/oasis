#set($symbol_pound='#')#set($symbol_dollar='$')#set($symbol_escape='\')package ${package}.authc;

import ${package}.authc.entity.KeyUsernamePwdToken;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.credential.CredentialsMatcher;

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
        String realPwd = info.getCredentials().toString();
        String pwd = String.valueOf((char[]) t.getCredentials());
        if (realPwd.equals(pwd)) {
            return true;
        }
        throw new IncorrectCredentialsException("证书错误（KEY/密码）与用户名不一致");
    }

}
