package cn.xyz.chaos.mvc.token.strategy;

import cn.xyz.chaos.mvc.token.Token;
import cn.xyz.chaos.mvc.web.RequestContext;

/**
 * cn.xyz.chaos.mvc.token Token 校验
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/1/30 15:41.
 */
public interface TokenStrategy {

    /**
     * 检查Token
     *
     * @param requestContext 请求上下文
     * @return 合法返回True
     */
    boolean check(final RequestContext requestContext);

    /**
     * 生成新的token
     *
     * @param requestContext 请求上下文
     * @return Token
     */
    Token generateNew(final RequestContext requestContext);

}
