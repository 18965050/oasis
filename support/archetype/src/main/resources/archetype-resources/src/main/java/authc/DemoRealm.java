#set($symbol_pound='#')#set($symbol_dollar='$')#set($symbol_escape='\')package ${package}.authc;

import ${package}.authc.entity.AccInfo;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import ${package}.authc.entity.KeyUsernamePwdToken;import ${package}.web.service.AuthService;

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

    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        AccInfo accInfo = (AccInfo) principals.getPrimaryPrincipal();
        return accInfo.getId().toString();
    }
}
