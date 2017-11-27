package cn.xyz.chaos.mvc.abuse.discriminator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xyz.chaos.mvc.abuse.TagConstants;
import cn.xyz.chaos.mvc.web.RequestContext;

/**
 * cn.xyz.chaos.mvc.abuse.discriminator
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/4 16:09.
 */
public class SpiderDiscriminator implements Discriminator {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpiderDiscriminator.class);

    public static final String LEGAL_INFO = "legal";

    private List<String> legalSpiderAgents;

    @Override
    public boolean discriminate(RequestContext requestContext) {
        HttpServletRequest request = requestContext.getRequest();
        String userAgent = request.getHeader("User-Agent");
        if (StringUtils.isNotBlank(userAgent) && CollectionUtils.isNotEmpty(legalSpiderAgents)) {
            String lowerCaseUserAgent = userAgent.toLowerCase();
            for (String legalAgent : legalSpiderAgents) {
                if (lowerCaseUserAgent.contains(legalAgent.toLowerCase())) {
                    // 确认为爬虫，则不再继续
                    LOGGER.trace("请求确认为爬虫，不再继续鉴别！");
                    requestContext.tagged(TagConstants.SPIDER, LEGAL_INFO);
                    return false;
                }
            }
        }
        return true;
    }

    public void setLegalSpiderAgents(List<String> legalSpiderAgents) {
        this.legalSpiderAgents = legalSpiderAgents;
    }
}
