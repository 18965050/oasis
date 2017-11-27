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
 * @version 1.0.0 Created by lcg on 2015/2/4 17:45.
 */
public class SpiderHandler extends AbstractAbuseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpiderHandler.class);

    @Override
    protected boolean doHandle(RequestContext requestContext) {
        // 暂时只做白名单，黑名单另加吧
        LOGGER.trace("请求来自合法爬虫，直接放行！");
        requestContext.setBlocked(false);
        return false;
    }

    @Override
    public List<String> getSupports() {
        return ImmutableList.of(TagConstants.SPIDER);
    }
}
