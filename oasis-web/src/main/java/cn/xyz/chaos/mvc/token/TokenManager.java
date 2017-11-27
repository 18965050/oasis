package cn.xyz.chaos.mvc.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import cn.xyz.chaos.mvc.token.strategy.TokenStrategy;
import cn.xyz.chaos.mvc.web.RequestContext;
import cn.xyz.chaos.mvc.web.RequestMatcher;

import com.google.common.base.Preconditions;

/**
 * cn.xyz.chaos.mvc.token Token 管理器<br/>
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/1/30 15:26.
 */
public class TokenManager implements InitializingBean {

    private Logger LOGGER = LoggerFactory.getLogger(TokenManager.class);

    private RequestMatcher excludeMatcher;
    private RequestMatcher includeMatcher;
    private TokenStrategy tokenStrategy;

    public boolean validate(HttpServletRequest request, HttpServletResponse response) {
        RequestContext requestContext = RequestContext.getRequestContext(request, response);
        if (isInclude(requestContext)) {
            LOGGER.debug("请求RUI ：[{}] 需要Token验证", requestContext.getURI());
            return tokenStrategy.check(requestContext);
        }
        return true;
    }

    /**
     * 是否需要TokenManager验证有效性<br/>
     * 根据exclude规则和include规则综合而来,优先匹配exclude规则
     *
     * @param requestContext 请求上下文
     * @return true（需要处理），否则false
     */
    public boolean isInclude(RequestContext requestContext) {
        return (null == excludeMatcher || !excludeMatcher.match(requestContext)) && null != includeMatcher
                && includeMatcher.match(requestContext);
    }

    /**
     * 生成新的Token
     *
     * @param request
     * @param response
     * @return
     */
    public Token newToken(HttpServletRequest request, HttpServletResponse response) {
        RequestContext requestContext = RequestContext.getRequestContext(request, response);
        return tokenStrategy.generateNew(requestContext);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(tokenStrategy, "Token 校验器不可为Null");
        Preconditions.checkNotNull(includeMatcher, "Token 匹配规则不可为Null，请设置includeMatcher");
        if (null == excludeMatcher) {
            LOGGER.info("Token 校验器的排除规则没有配置！如果需要，请设置excludeMatcher。");
        }
    }

    public void setExcludeMatcher(RequestMatcher excludeMatcher) {
        this.excludeMatcher = excludeMatcher;
    }

    public void setIncludeMatcher(RequestMatcher includeMatcher) {
        this.includeMatcher = includeMatcher;
    }

    public void setTokenStrategy(TokenStrategy tokenStrategy) {
        this.tokenStrategy = tokenStrategy;
    }
}
