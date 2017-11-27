package cn.xyz.chaos.mvc.web;

/**
 * cn.xyz.chaos.mvc.web 请求匹配器
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/1/30 15:32.
 */
public interface RequestMatcher {

    /**
     * 判断请求是否匹配
     *
     * @param requestContext 请求上下文
     * @return 匹配为true
     */
    boolean match(final RequestContext requestContext);

}
