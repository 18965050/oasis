package cn.xyz.chaos.mvc.web;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;

import cn.xyz.chaos.common.utils.IPUtils;

/**
 * cn.xyz.chaos.mvc.web
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/1/30 15:24.
 */
public class RequestContext {

    public static final String REQUEST_CONTEXT_KEY = "__requestContext";
    private HttpServletRequest request;

    private HttpServletResponse response;
    private HttpSession session;
    private String URI;
    private String ip;

    private boolean blocked;

    private Map<String, String> tagMap;

    private RequestContext(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.URI = request.getRequestURI();
        this.session = request.getSession();
        this.ip = IPUtils.getIP(this.request);
        // 滥用检查基本上会被配置以处理每一个请求，故在此处初始化map
        tagMap = new HashMap<String, String>();
    }

    /**
     * 给该请求上下文打标签，标签的key与信息都为tag<br/>
     * 可参考 {@link #tagged(String, String)}
     *
     * @see #tagged(String, String)
     * @param tag 标签
     */
    public void tagged(String tag) {
        tagged(tag, tag);
    }

    /**
     * 给该请求上下文打标签
     *
     * @param tag 标签（标签类别）
     * @param tagInfo 标签信息
     */
    public void tagged(String tag, String tagInfo) {
        tagMap.put(tag, tagInfo);
    }

    /**
     * 是否包含标签
     *
     * @param tag 标签
     * @return 包含则true
     */
    public boolean hasTag(String tag) {
        return tagMap.containsKey(tag);
    }

    /**
     * 至少包含 tags 的其中一个？
     *
     * @param tags 标签
     * @return 如果参数tags为空，或者所有的标签都不被包含则返回false，否则返回true
     */
    public boolean hasAtLeastOne(Collection<String> tags) {
        if (CollectionUtils.isNotEmpty(tags)) {
            for (String tag : tags) {
                if (tagMap.containsKey(tag))
                    return true;
            }
        }
        return false;
    }

    /**
     * 包含所有Tag?
     *
     * @param tags 标签
     * @return 如果参数tags为空或者其所有标签都被包含则返回true，否则返回false
     */
    public boolean hasAll(Collection<String> tags) {
        if (CollectionUtils.isNotEmpty(tags)) {
            for (String tag : tags) {
                if (!tagMap.containsKey(tag))
                    return false;
            }
        }
        return true;
    }

    /**
     * 返回标签的标签信息<br/>
     * 注意，存在标签，且设置时如果未设置标签信息，拿到的结果就是标签本身<br/>
     *
     * @param tag 标签
     * @return 未设置过标签则为null，设置过则为对应信息
     */
    public String getTagInfo(String tag) {
        return tagMap.get(tag);
    }

    public String getIp() {
        return ip;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public HttpSession getSession() {
        return session;
    }

    public String getURI() {
        return URI;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    /**
     * 获取RequestContext，如果已经存在则使用现有的，否则根据request和response创建新的。<br/>
     * 该RequestContext 以key {@link #REQUEST_CONTEXT_KEY} 作为Request的Attribute
     *
     * @param request 请求
     * @param response 响应
     * @return
     */
    public static RequestContext getRequestContext(HttpServletRequest request, HttpServletResponse response) {
        RequestContext requestContext = (RequestContext) request.getAttribute(REQUEST_CONTEXT_KEY);
        if (null == requestContext) {
            requestContext = new RequestContext(request, response);
            request.setAttribute(REQUEST_CONTEXT_KEY, requestContext);
        }
        return requestContext;
    }
}
