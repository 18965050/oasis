package cn.xyz.chaos.mvc.abuse;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import cn.xyz.chaos.mvc.abuse.discriminator.Discriminator;
import cn.xyz.chaos.mvc.abuse.handler.AbuseHandler;
import cn.xyz.chaos.mvc.web.RequestContext;
import cn.xyz.chaos.mvc.web.RequestMatcher;

import com.google.common.base.Preconditions;

/**
 * cn.xyz.chaos.mvc.abuse 访问控制类
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/3 13:45.
 */
public class AbuseManager implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbuseManager.class);

    private List<Discriminator> discriminators;

    private List<AbuseHandler> abuseHandlers;

    private RequestMatcher excludeMatcher;

    private AbuseInfoKeeper infoKeeper;

    public boolean allow(HttpServletRequest request, HttpServletResponse response) {
        RequestContext requestContext = RequestContext.getRequestContext(request, response);
        requestTouch(requestContext);
        if (isExclude(requestContext)) {
            LOGGER.debug("请求RUI ：[{}] 不需要访问控制！", requestContext.getURI());
            return true;
        }
        tagged(requestContext);
        LOGGER.trace("请求识别结束！");
        handle(requestContext);
        boolean blocked = requestContext.isBlocked();
        LOGGER.trace("请求处理结束！，是否阻止访问？ {}", blocked);
        return !blocked;
    }

    /**
     * 根据识别结果进行处理
     *
     * @param requestContext
     */
    private void handle(RequestContext requestContext) {
        if (CollectionUtils.isNotEmpty(abuseHandlers)) {
            for (AbuseHandler abuseHandler : abuseHandlers) {
                if (!abuseHandler.handle(requestContext)) {
                    // 返回false则不继续处理
                    return;
                }
            }
        }
    }

    /**
     * 鉴别请求（来源、类型等)并打标签
     *
     * @param requestContext
     */
    private void tagged(RequestContext requestContext) {
        if (CollectionUtils.isNotEmpty(discriminators)) {
            for (Discriminator discriminator : discriminators) {
                if (!discriminator.discriminate(requestContext)) {
                    // 返回false则不继续处理
                    return;
                }
            }
        }
    }

    /**
     * 记录访问增加
     *
     * @param requestContext
     */
    private void requestTouch(RequestContext requestContext) {
        infoKeeper.touch(requestContext);
    }

    private boolean isExclude(RequestContext requestContext) {
        return null != excludeMatcher && excludeMatcher.match(requestContext);
    }

    public void setExcludeMatcher(RequestMatcher excludeMatcher) {
        this.excludeMatcher = excludeMatcher;
    }

    public void setDiscriminators(List<Discriminator> discriminators) {
        this.discriminators = discriminators;
    }

    public void setAbuseHandlers(List<AbuseHandler> abuseHandlers) {
        this.abuseHandlers = abuseHandlers;
    }

    public void setInfoKeeper(AbuseInfoKeeper infoKeeper) {
        this.infoKeeper = infoKeeper;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(infoKeeper);
        if (CollectionUtils.isEmpty(discriminators)) {
            LOGGER.warn("滥用检查请求识别器为空!");
        }
        if (CollectionUtils.isEmpty(abuseHandlers)) {
            LOGGER.warn("滥用检查请求处理器为空!");
        }
    }
}
