package cn.xyz.chaos.examples.showcase.authc;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.xyz.chaos.examples.showcase.authc.entity.KeyUsernamePwdToken;
import cn.xyz.chaos.examples.showcase.web.service.AuthService;

/**
 * DemoRealm <br/>
 * 示例认证授权
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年6月25日 下午7:55:36
 * @author lcg
 */
public class DemoRealm extends AuthorizingRealm {

    @Autowired
    private AuthService authService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 没有权限
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        KeyUsernamePwdToken loginToken = (KeyUsernamePwdToken) token;
        return authService.getAuthInfo(loginToken, getName());
    }

    /**
     * @return the authService
     */
    public AuthService getAuthService() {
        return authService;
    }

    /**
     * @param authService the authService to set
     */
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

}
