package cn.xyz.chaos.examples.demo.authc;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import cn.xyz.chaos.examples.demo.authc.entity.AccInfo;
import cn.xyz.chaos.examples.demo.authc.entity.KeyUsernamePwdToken;
import cn.xyz.chaos.examples.demo.provider.consts.UserInfoConst;
import cn.xyz.chaos.examples.demo.web.service.AuthService;

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
        AccInfo accInfo = (AccInfo) getAvailablePrincipal(principals);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        String roleName = "";
        switch (accInfo.getType()) {
        case UserInfoConst.TYPE_NORMAL:
            roleName = UserInfoConst.ROLE_NORMAL;
            break;
        case UserInfoConst.TYPE_ADMIN:
            roleName = UserInfoConst.ROLE_ADMIN;
            break;
        default:
            roleName = UserInfoConst.ROLE_NORMAL;
            break;
        }
        authorizationInfo.addRole(roleName);
        return authorizationInfo;
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

    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        AccInfo accInfo = (AccInfo) getAvailablePrincipal(principals);
        return accInfo.getId().toString();
    }
}
