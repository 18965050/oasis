package cn.xyz.chaos.mvc.token.strategy;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xyz.chaos.mvc.token.ClientTokenReader;
import cn.xyz.chaos.mvc.token.Token;
import cn.xyz.chaos.mvc.web.RequestContext;

/**
 * cn.xyz.chaos.mvc.token.strategy
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/2 11:02.
 */
public abstract class AbstractTokenStrategy implements TokenStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractTokenStrategy.class);

    @Override
    public boolean check(RequestContext requestContext) {
        ClientTokenReader clientTokenReader = getClientTokenReader();
        Token token = clientTokenReader.read(requestContext);
        if (null == token) {
            LOGGER.info("来自IP：{} 的请求 ：[{}] 未成功提取Token，校验失败！", requestContext.getIp(), requestContext.getURI());
            return false;
        }
        return doCheck(requestContext, token);
    }

    @Override
    public Token generateNew(RequestContext requestContext) {
        Token token = newToken(requestContext);
        afterTokenGenerate(requestContext, token);
        return token;
    }

    /**
     * 生成一个新的孤立的Token
     *
     * @param requestContext 请求上下文
     * @return
     */
    protected Token newToken(RequestContext requestContext) {
        return new Token(generateSecret(), requestContext.getIp());
    }

    /**
     * 生成Token secret 字段<br/>
     * 此处简单随机32位字符串
     *
     * @return
     */
    protected String generateSecret() {
        return RandomStringUtils.randomAlphanumeric(32);
    }

    /**
     * 具体校验token是否合法
     *
     * @param requestContext 请求上下文
     * @param token 客户端提取的token
     */
    protected abstract boolean doCheck(RequestContext requestContext, Token token);

    /**
     * Token生成后处理
     *
     * @param requestContext
     * @param token
     */
    protected abstract void afterTokenGenerate(RequestContext requestContext, Token token);

    public abstract ClientTokenReader getClientTokenReader();

}
