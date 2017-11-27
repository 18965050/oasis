package cn.xyz.chaos.mvc.abuse.integration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.xyz.chaos.mvc.abuse.AbuseManager;

import com.google.common.base.Preconditions;

/**
 * cn.xyz.chaos.mvc.abuse.integration 滥用拦截，一定程度阻止恶意请求<br/>
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/3 16:00.
 */
public class AbuseInterceptor extends HandlerInterceptorAdapter implements InitializingBean {

    private static Logger LOGGER = LoggerFactory.getLogger(AbuseInterceptor.class);

    /**
     * token Manager
     */
    private AbuseManager abuseManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        return abuseManager.allow(request, response);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(abuseManager, "abuseManager 不能为Null，请设置AbuseManager实例");
    }

    public void setAbuseManager(AbuseManager abuseManager) {
        this.abuseManager = abuseManager;
    }
}
