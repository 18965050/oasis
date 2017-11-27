package cn.xyz.chaos.mvc.abuse.discriminator;

import cn.xyz.chaos.mvc.abuse.TagConstants;
import cn.xyz.chaos.mvc.web.RequestContext;

/**
 * cn.xyz.chaos.mvc.abuse.discriminator
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/4 17:32.
 */
public class NormalRequestDiscriminator implements Discriminator {

    @Override
    public boolean discriminate(RequestContext requestContext) {
        requestContext.tagged(TagConstants.NORMAL);
        return true;
    }
}
