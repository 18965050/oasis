package cn.xyz.chaos.mvc.abuse.discriminator;

import java.util.List;

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
 * @version 1.0.0 Created by lcg on 2015/2/4 16:41.
 */
public class RegisteredIPDiscriminator implements Discriminator {

    private static final Logger LOGGER = LoggerFactory.getLogger(RegisteredIPDiscriminator.class);

    private static final String LEGAL_INFO = "legal";

    private List<String> legalIps;

    @Override
    public boolean discriminate(RequestContext requestContext) {
        String ip = requestContext.getIp();
        if (StringUtils.isNoneBlank(ip) && CollectionUtils.isNotEmpty(legalIps) && legalIps.contains(ip)) {
            // 确认为已登记IP，则不再继续
            LOGGER.trace("请求来自已登记IP，不再继续鉴别");
            requestContext.tagged(TagConstants.IP_REGISTERED, LEGAL_INFO);
            return false;
        }
        return true;
    }

    public void setLegalIps(List<String> legalIps) {
        this.legalIps = legalIps;
    }
}
