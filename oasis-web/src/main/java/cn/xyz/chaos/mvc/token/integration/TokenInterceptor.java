package cn.xyz.chaos.mvc.token.integration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.xyz.chaos.common.utils.WebHelper;
import cn.xyz.chaos.mvc.token.TokenGetterWrapper;
import cn.xyz.chaos.mvc.token.TokenManager;

import com.google.common.base.Preconditions;

/**
 * cn.xyz.chaos.mvc.token.integration 令牌拦截，一定程度防止CSRF,并防止重复提交<br/>
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/1/30 16:00.
 */
public class TokenInterceptor extends HandlerInterceptorAdapter implements InitializingBean {

    private static Logger LOGGER = LoggerFactory.getLogger(TokenInterceptor.class);

    public static final String DEFAULT_TOKEN_GETTER_WRAPPER_KEY = "_newToken";

    private String tokenGetterWrapperKey = DEFAULT_TOKEN_GETTER_WRAPPER_KEY;

    /**
     * token 校验失败跳转ULR
     */
    private String redirectUrl;

    /**
     * token Manager
     */
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        boolean validate = tokenManager.validate(request, response);
        LOGGER.debug("token 校验，校验结果: {}", validate);
        if (!validate) {
            WebHelper.redirect(request, response, redirectUrl);
        } else {
            TokenGetterWrapper tokenGetterWrapper = new TokenGetterWrapper(request, response, tokenManager);
            request.setAttribute(tokenGetterWrapperKey, tokenGetterWrapper);
        }
        return validate;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public void setTokenManager(TokenManager tokenManager) {
        this.tokenManager = tokenManager;
    }

    public void setTokenGetterWrapperKey(String tokenGetterWrapperKey) {
        this.tokenGetterWrapperKey = tokenGetterWrapperKey;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkArgument(StringUtils.isNotBlank(redirectUrl), "redirectUrl 不能为空！请设置Token校验失败的跳转地址！");
        Preconditions.checkNotNull(tokenManager, "tokenManager 不能为Null，请设置TokenManager实例");
    }
}
