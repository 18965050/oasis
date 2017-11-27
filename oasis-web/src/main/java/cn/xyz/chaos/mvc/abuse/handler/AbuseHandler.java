package cn.xyz.chaos.mvc.abuse.handler;

import cn.xyz.chaos.mvc.web.RequestContext;

/**
 * cn.xyz.chaos.mvc.abuse.handler 滥用检查处理器，根据请求类型做响应处理
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/3 20:30.
 */
public interface AbuseHandler {

    /**
     * 处理请求决定请求是否会被阻止
     *
     * @param requestContext 请求上下文
     * @return 如果不需要被其他处理器处理则返回false，否则true
     */
    boolean handle(RequestContext requestContext);

}
