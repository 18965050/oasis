package cn.xyz.chaos.mvc.abuse.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xyz.chaos.mvc.web.RequestContext;

/**
 * cn.xyz.chaos.mvc.abuse.handler
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/4 16:46.
 */
public abstract class AbstractAbuseHandler implements AbuseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractAbuseHandler.class);

    @Override
    public boolean handle(RequestContext requestContext) {
        if (support(requestContext)) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("AbuseHandler {} 支持处理该类型请求，进行处理", this.getClass().getSimpleName());
            }
            return doHandle(requestContext);
        }
        return true;
    }

    protected abstract boolean doHandle(RequestContext requestContext);

    protected boolean support(RequestContext requestContext) {
        return requestContext.hasAtLeastOne(getSupports());
    }

    public abstract List<String> getSupports();

}
