package cn.xyz.chaos.examples.demo.authc;

import javax.naming.AuthenticationException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import cn.xyz.chaos.common.utils.PropertiesLoader;
import cn.xyz.chaos.examples.demo.authc.entity.AccInfo;
import cn.xyz.chaos.examples.demo.authc.entity.KeyUsernamePwdToken;

/**
 * cn.xyz.chaos.examples.demo.authc
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2014/9/1 18:44.
 */
public class AuthUtils {

    /**
     * 登陆
     *
     * @param token
     * @throws AuthenticationException
     */
    public static void login(KeyUsernamePwdToken token) throws AuthenticationException {
        SecurityUtils.getSubject().login(token);
    }

    public static void logout() {
        SecurityUtils.getSubject().logout();
    }

    /**
     * 获取当前用户
     *
     * @return
     */
    public static AccInfo getCurrentUser() {
        Subject subject = SecurityUtils.getSubject();
        return null == subject || null == subject.getPrincipal() ? null : (AccInfo) subject.getPrincipal();
    }

    /**
     * 判断当前用户是否已登录
     *
     * @return
     */
    public static boolean isLogined() {
        Subject subject = SecurityUtils.getSubject();
        return null != subject && subject.isAuthenticated();
    }

}
