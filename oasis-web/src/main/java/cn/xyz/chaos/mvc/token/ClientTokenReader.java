package cn.xyz.chaos.mvc.token;

import cn.xyz.chaos.mvc.web.RequestContext;

/**
 * cn.xyz.chaos.mvc.token 客户端 Token 读取
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/1/30 15:48.
 */
public interface ClientTokenReader {

    Token read(RequestContext requestContext);

}
