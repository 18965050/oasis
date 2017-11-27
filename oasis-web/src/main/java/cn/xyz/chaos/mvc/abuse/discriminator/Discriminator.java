package cn.xyz.chaos.mvc.abuse.discriminator;

import cn.xyz.chaos.mvc.web.RequestContext;

/**
 * cn.xyz.chaos.mvc.abuse.discriminator 请求鉴别器，鉴别请求的来源、类型等属性
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/3 19:13.
 */
public interface Discriminator {

    /**
     * 鉴别，对RequestContext 打标签
     *
     * @param requestContext 请求上下文
     * @return 不需要其他鉴别器再继续鉴别则返回false，否则为true
     */
    boolean discriminate(RequestContext requestContext);

}
