package cn.xyz.chaos.mvc.abuse;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import cn.xyz.chaos.mvc.abuse.store.Store;
import cn.xyz.chaos.mvc.web.RequestContext;

import com.google.common.base.Preconditions;

/**
 * cn.xyz.chaos.mvc.abuse
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/4 10:28.
 */
public class AbuseLocker implements InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbuseLocker.class);
    private static final String DEFAULT_STORE_NAME = "__abuse:lock:";
    private static final String LOCKER_IP_PREFIX = "ipl:";
    public static final String IP_LOCKED_KEY = "__abuse:ip_locked";
    public static final String SESSION_LOCKED_KEY = "__abuse:session_locked";
    private static final String SESSION_LOCKED_TIME_KEY = "__abuse:session_locked_time";
    public static final String SESSION_UNLOCKED_KEY = "__abuse:session_unlocked";
    private static final String SESSION_UNLOCKED_TIME_KEY = "__abuse:session_unlocked_time";

    private Store store;

    public void lockIP(RequestContext requestContext, int expiration) {
        store.save(LOCKER_IP_PREFIX + requestContext.getIp(), System.currentTimeMillis(), expiration);
        requestContext.getRequest().setAttribute(IP_LOCKED_KEY, true);
    }

    public void lockSession(RequestContext requestContext, int expiration) {
        HttpSession session = requestContext.getSession();
        session.setAttribute(SESSION_LOCKED_KEY, true);
        session.setAttribute(SESSION_LOCKED_TIME_KEY, System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 解锁，只针对Session解锁，因为正常用户都有Session。<br/>
     * 意味着IP被锁定后，使用新用户访问或者无Cookie访问仍会被锁定。
     *
     * @param requestContext
     * @param expiration
     */
    public void unlock(RequestContext requestContext, int expiration) {
        HttpSession session = requestContext.getSession();
        session.setAttribute(SESSION_UNLOCKED_KEY, true);
        session.setAttribute(SESSION_UNLOCKED_TIME_KEY, System.currentTimeMillis() + expiration * 1000);
        session.removeAttribute(SESSION_LOCKED_KEY);
        session.removeAttribute(SESSION_LOCKED_TIME_KEY);
    }

    public boolean isIPLocked(RequestContext requestContext) {
        Boolean locked = (Boolean) requestContext.getRequest().getAttribute(IP_LOCKED_KEY);
        if (null == locked) {
            locked = store.exist(LOCKER_IP_PREFIX + requestContext.getIp());
        }
        return locked;
    }

    public boolean isSessionLocked(RequestContext requestContext) {
        HttpSession session = requestContext.getSession();
        Boolean locked = (Boolean) session.getAttribute(SESSION_LOCKED_KEY);
        if (null != locked && locked) {
            Long t = (Long) session.getAttribute(SESSION_LOCKED_TIME_KEY);
            if (null != t && t > System.currentTimeMillis()) {
                return true;
            } else {
                session.removeAttribute(SESSION_LOCKED_KEY);
                session.removeAttribute(SESSION_LOCKED_TIME_KEY);
            }
        }
        return false;
    }

    public boolean isLocked(RequestContext requestContext) {
        return isSessionLocked(requestContext) || isIPLocked(requestContext);
    }

    public boolean isUnlocked(RequestContext requestContext) {
        HttpSession session = requestContext.getSession();
        Boolean unlocked = (Boolean) session.getAttribute(SESSION_UNLOCKED_KEY);
        if (null != unlocked && unlocked) {
            Long t = (Long) session.getAttribute(SESSION_UNLOCKED_TIME_KEY);
            if (null != t && t > System.currentTimeMillis()) {
                return true;
            } else {
                session.removeAttribute(SESSION_UNLOCKED_KEY);
                session.removeAttribute(SESSION_UNLOCKED_TIME_KEY);
            }
        }
        return false;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Preconditions.checkNotNull(store);
        if (StringUtils.isEmpty(store.getName())) {
            store.setName(DEFAULT_STORE_NAME);
        }
    }
}
