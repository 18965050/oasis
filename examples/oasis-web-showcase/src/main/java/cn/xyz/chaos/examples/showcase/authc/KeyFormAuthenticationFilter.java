package cn.xyz.chaos.examples.showcase.authc;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;

import cn.xyz.chaos.examples.showcase.authc.entity.KeyUsernamePwdToken;

/**
 * KeyFormAuthenticationFilter <br/>
 * 登陆表单验证，处理Token创建，处理登陆成功失败流程
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年6月29日 下午3:38:25
 * @author lcg
 */
public class KeyFormAuthenticationFilter extends FormAuthenticationFilter {

    public static final String DEFAULT_KEY_PARAM = "key";
    private String keyParam = DEFAULT_KEY_PARAM;
    public static final String AJAX_HEADER_NAME = "X-Requested-With";
    public static final String AJAX_HEADER_VALUE = "XMLHttpRequest";

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) {
        String username = getUsername(request);
        String password = getPassword(request);
        boolean rememberMe = isRememberMe(request);
        String key = getKey(request);
        String host = getHost(request);
        return new KeyUsernamePwdToken(username, password, rememberMe, host, key);
    }

    @Override
    protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request,
            ServletResponse response) throws Exception {
        if (isAjaxRequest(request)) {
            // TODO AJAX 请求处理
        }
        return super.onLoginSuccess(token, subject, request, response);
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
            ServletResponse response) {
        if (isAjaxRequest(request)) {
            // TODO AJAX 请求处理
        }
        return super.onLoginFailure(token, e, request, response);
    }

    @Override
    protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
        String errorMessage = null;
        if (ae instanceof ConcurrentAccessException) {
            // 并发访问（同账号多处登陆）
        } else if (ae instanceof LockedAccountException) {
            // 锁定（DisabledAccountException的一种）
        } else if (ae instanceof UnknownAccountException || ae instanceof DisabledAccountException
                || ae instanceof IncorrectCredentialsException) {
            // 已定义错误消息
            errorMessage = ae.getMessage();
        } else {
            // 非已确定的账号异常，可能是服务器异常
            errorMessage = "服务器异常，请稍后再试！";
        }
        request.setAttribute(getFailureKeyAttribute(), errorMessage);
    }

    /**
     * @param request
     * @return
     */
    protected String getKey(ServletRequest request) {
        return WebUtils.getCleanParam(request, getKeyParam());
    }

    /**
     * @return the keyParam
     */
    public String getKeyParam() {
        return keyParam;
    }

    /**
     * @param keyParam the keyParam to set
     */
    public void setKeyParam(String keyParam) {
        this.keyParam = keyParam;
    }

    /**
     * 判断是否AJAX请求
     *
     * @param request
     * @return
     */
    public boolean isAjaxRequest(ServletRequest request) {
        if (request instanceof HttpServletRequest) {
            String header = ((HttpServletRequest) request).getHeader(AJAX_HEADER_NAME);
            return AJAX_HEADER_VALUE.equals(header);
        }
        return false;
    }
}
