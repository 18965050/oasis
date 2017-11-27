package cn.xyz.chaos.mvc.token.strategy;

import java.util.*;

import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xyz.chaos.mvc.token.ClientTokenReader;
import cn.xyz.chaos.mvc.token.Token;
import cn.xyz.chaos.mvc.web.RequestContext;

/**
 * cn.xyz.chaos.mvc.token.strategy
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/2 11:24.
 */
public class ServerStoreTokenStrategy extends AbstractTokenStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServerStoreTokenStrategy.class);

    /**
     * token 在Session中存放的key
     */
    private String tokenKeyInSession;

    private ClientTokenReader clientTokenReader;

    /**
     * 最大Token数量
     */
    private int maxStoreTokenNum;

    /**
     * 过期时间，单位s
     */
    private int expiration;

    private boolean needSync = true;

    @Override
    protected boolean doCheck(RequestContext requestContext, Token token) {
        SortedSet<Token> tokens = getStoredTokens(requestContext);
        if (CollectionUtils.isEmpty(tokens)) {
            return false;
        }
        boolean rs;
        if (needSync) {
            synchronized (tokens) {
                rs = checkExistAndRemove(token, tokens);
            }
        } else {
            rs = checkExistAndRemove(token, tokens);
        }
        if (rs) {
            // true 的情况下会删除原 token，需要保存
            storeTokens(requestContext, tokens);
        }
        return rs;
    }

    /**
     * 将Token保存在服务端
     *
     * @param requestContext
     * @param token
     */
    @Override
    protected void afterTokenGenerate(RequestContext requestContext, Token token) {
        SortedSet<Token> tokens = getStoredTokens(requestContext);
        if (null == tokens) {
            // 如果需要同步操作则使用 Collections.synchronizedSortedSet
            tokens = needSync ? Collections.synchronizedSortedSet(new TreeSet<Token>()) : new TreeSet<Token>();
        }
        tokens.add(token);
        LOGGER.debug("{}-{} 服务端存储Token,当前Token数量{}", requestContext.getIp(), requestContext.getSession().getId(),
                tokens.size());
        if (tokens.size() > maxStoreTokenNum) {
            // 此处不需要考虑 synchronized ，根据 needSync 自动判断是否使用线程安全的实现
            tokens.remove(tokens.first());
            LOGGER.debug("{}-{} 服务端存储Token超出限制,清除掉最早的一条，清除后Token数量{}", requestContext.getIp(),
                    requestContext.getSession().getId(), tokens.size());
        }
        storeTokens(requestContext, tokens);
    }

    @Override
    public ClientTokenReader getClientTokenReader() {
        return clientTokenReader;
    }

    public void setClientTokenReader(ClientTokenReader clientTokenReader) {
        this.clientTokenReader = clientTokenReader;
    }

    /**
     * 检查 token 的 Secret 是否在 tokens 中存在。如果存在，则删除 tokens 对应的记录。 <br/>
     * 因为 token 使用<b>时间</b> compare，我们期望比较的是 Secret，所以不能用集合的 contains
     * @param token 要校验的 token
     * @param tokens 保存的 token 集合
     * @return 存在则true
     */
    private boolean checkExistAndRemove(Token token, SortedSet<Token> tokens) {
        // 如果可以倒序，则倒序比较（大部分情况下，校验的都是后创建的）
        Iterator<Token> iterator = (tokens instanceof NavigableSet) ? ((NavigableSet) tokens).descendingIterator()
                : tokens.iterator();
        while (iterator.hasNext()) {
            Token t = iterator.next();
            if (t.getSecret().equals(token.getSecret())) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * 获取目前存储在服务端的的TokenSet
     *
     * @param requestContext 请求上下文
     * @return maybe null or emptySet
     */
    private SortedSet<Token> getStoredTokens(RequestContext requestContext) {
        HttpSession session = requestContext.getSession();
        SortedSet<Token> tokens = (SortedSet<Token>) session.getAttribute(tokenKeyInSession);
        if (expiration > 0 && CollectionUtils.isNotEmpty(tokens)) {
            long l = new Date().getTime() - expiration * 1000;
            if (tokens.first().getCreateTime().getTime() < l) {
                // 存在过期的Token，需要清理
                LOGGER.info("{}-{} 存在过期的Token，进行清理，清理前Token数量：{}", requestContext.getIp(),
                        requestContext.getSession().getId(), tokens.size());
                Token token = new Token(StringUtils.EMPTY, StringUtils.EMPTY, new Date(l));
                // 此处不需要考虑 synchronized ，根据 needSync 自动判断是否使用线程安全的实现
                tokens = tokens.tailSet(token);
                LOGGER.info("{}-{} 存在过期的Token，进行清理，清理后Token数量：{}", requestContext.getIp(),
                        requestContext.getSession().getId(), tokens.size());
                storeTokens(requestContext, tokens);
            }
        }
        return tokens;
    }

    private void storeTokens(RequestContext requestContext, SortedSet<Token> tokens) {
        HttpSession session = requestContext.getSession();
        session.setAttribute(tokenKeyInSession, tokens);
    }

    public void setTokenKeyInSession(String tokenKeyInSession) {
        this.tokenKeyInSession = tokenKeyInSession;
    }

    public void setMaxStoreTokenNum(int maxStoreTokenNum) {
        this.maxStoreTokenNum = maxStoreTokenNum;
    }

    public void setExpiration(int expiration) {
        this.expiration = expiration;
    }

    public void setNeedSync(boolean needSync) {
        this.needSync = needSync;
    }
}
