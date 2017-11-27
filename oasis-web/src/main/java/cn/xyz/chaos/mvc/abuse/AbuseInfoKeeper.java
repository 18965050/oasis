package cn.xyz.chaos.mvc.abuse;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;

import cn.xyz.chaos.mvc.abuse.store.Store;
import cn.xyz.chaos.mvc.web.RequestContext;

import com.google.common.base.Preconditions;

/**
 * cn.xyz.chaos.mvc.abuse
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/4 11:37.
 */
public class AbuseInfoKeeper implements InitializingBean {

    private static final String DEFAULT_STORE_NAME = "__abuse:info:";
    public static final String IP_VISIT_KEY = "__ip_v";
    public static final String SESSION_VISIT_KEY = "__session_v";
    public static final String IP_VISIT_TIME_KEY = "__ip_v_t";
    public static final String IP_VISIT_SEPARATOR = ":";
    private static final String IP_VISIT_SESSION_CREATE_KEY = "__ip_v_s_c";

    private Store store;
    private int ipExpiration;

    public void touch(RequestContext requestContext) {
        incrIpVisit(requestContext);
        incrSessionVisit(requestContext);
    }

    private void incrSessionVisit(RequestContext requestContext) {
        HttpSession session = requestContext.getSession();
        Integer sessionVisit = (Integer) session.getAttribute(SESSION_VISIT_KEY);
        if (null == sessionVisit) {
            sessionVisit = 0;
        }
        sessionVisit = sessionVisit + 1;
        session.setAttribute(SESSION_VISIT_KEY, sessionVisit);
    }

    private void incrIpVisit(RequestContext requestContext) {
        String ip = requestContext.getIp();
        String ipVisitInfo = store.get(ip);
        if (null == ipVisitInfo) {
            resetIpVisit(requestContext);
        } else {
            String[] split = ipVisitInfo.split(IP_VISIT_SEPARATOR);
            Date ipTime = new Date(Long.parseLong(split[0]));
            int ipVisit = Integer.parseInt(split[1]) + 1;
            int sessionCreate = Integer.parseInt(split[2]);
            if (requestContext.getSession().isNew()) {
                sessionCreate += 1;
            }
            resetIpVisit(requestContext, ipTime, ipVisit, sessionCreate);
        }
    }

    public void resetSessionVisit(RequestContext requestContext, int visitTimes) {
        HttpSession session = requestContext.getSession();
        session.setAttribute(SESSION_VISIT_KEY, visitTimes);
    }

    public void resetIpVisit(RequestContext requestContext) {
        resetIpVisit(requestContext, new Date(), 1, 1);
    }

    public void resetIpVisit(RequestContext requestContext, Date time, int visitTimes, int sessionCreateTimes) {
        HttpServletRequest request = requestContext.getRequest();
        request.setAttribute(IP_VISIT_KEY, visitTimes);
        request.setAttribute(IP_VISIT_TIME_KEY, time);
        request.setAttribute(IP_VISIT_SESSION_CREATE_KEY, sessionCreateTimes);
        String s = new StringBuilder().append(time.getTime()).append(IP_VISIT_SEPARATOR).append(visitTimes)
                .append(IP_VISIT_SEPARATOR).append(sessionCreateTimes).toString();
        store.save(requestContext.getIp(), s, ipExpiration);
    }

    public int getIpVisit(RequestContext requestContext) {
        return (Integer) requestContext.getRequest().getAttribute(IP_VISIT_KEY);
    }

    public int getIpSessionCreate(RequestContext requestContext) {
        return (Integer) requestContext.getRequest().getAttribute(IP_VISIT_SESSION_CREATE_KEY);
    }

    public Long getIpPeriod(RequestContext requestContext) {
        Date visitTime = (Date) requestContext.getRequest().getAttribute(IP_VISIT_TIME_KEY);
        return System.currentTimeMillis() - visitTime.getTime();
    }

    public Long getSessionPeriod(RequestContext requestContext) {
        return System.currentTimeMillis() - (Long) requestContext.getSession().getCreationTime();
    }

    public int getSessionVisit(RequestContext requestContext) {
        return (Integer) requestContext.getSession().getAttribute(SESSION_VISIT_KEY);
    }

    public void setStore(Store store) {
        this.store = store;
    }

    /**
     * 设置IP记录信息的过期时间，单位秒。如果在过期时间内访问，会更新过期时间。
     *
     * @param ipExpiration
     */
    public void setIpExpiration(int ipExpiration) {
        this.ipExpiration = ipExpiration;
    }

    public Store getStore() {
        return store;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(store, "Store 不可为null");
        if (StringUtils.isEmpty(store.getName())) {
            store.setName(DEFAULT_STORE_NAME);
        }
    }
}
