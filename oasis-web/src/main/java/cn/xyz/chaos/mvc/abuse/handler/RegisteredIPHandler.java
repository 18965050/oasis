package cn.xyz.chaos.mvc.abuse.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xyz.chaos.mvc.abuse.TagConstants;
import cn.xyz.chaos.mvc.web.RequestContext;

import com.google.common.collect.ImmutableList;

/**
 * cn.xyz.chaos.mvc.abuse.handler
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/4 17:53.
 */
public class RegisteredIPHandler extends AbstractAbuseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisteredIPHandler.class);

    @Override
    protected boolean doHandle(RequestContext requestContext) {
        // 暂时只有白名单
        LOGGER.trace("请求来自已登记合法IP，直接放行！");
        requestContext.setBlocked(false);
        return false;
    }

    @Override
    public List<String> getSupports() {
        return ImmutableList.of(TagConstants.IP_REGISTERED);
    }
}
