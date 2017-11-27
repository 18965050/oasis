package cn.xyz.chaos.mvc.abuse.handler;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xyz.chaos.common.utils.WebHelper;
import cn.xyz.chaos.mvc.abuse.AbuseInfoKeeper;
import cn.xyz.chaos.mvc.abuse.AbuseLocker;
import cn.xyz.chaos.mvc.abuse.TagConstants;
import cn.xyz.chaos.mvc.web.RequestContext;

import com.google.common.collect.ImmutableList;

/**
 * cn.xyz.chaos.mvc.abuse.handler
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/4 17:59.
 */
public class IPSessionRateHandler extends AbstractAbuseHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(IPSessionRateHandler.class);

    private AbuseInfoKeeper infoKeeper;

    private AbuseLocker abuseLocker;

    private Map<Integer, Long> ipVisitCheckMap;

    private Map<Integer, Long> ipSessionCreateCheckMap;

    private Map<Integer, Long> sessionVisitCheckMap;

    private int ipBlockTime;

    private int sessionBlockTime;

    private String redirectUrl;

    @Override
    protected boolean doHandle(RequestContext requestContext) {
        if (!abuseLocker.isUnlocked(requestContext) && (abuseLocker.isLocked(requestContext) || !checkIp(requestContext)
                || !checkSession(requestContext))) {
            // 暂定规则为 非人为解锁 的前提下，已经被任何形式的锁定或新触发锁定，将阻止访问继续
            preventIt(requestContext);
        }
        return false;
    }

    private void preventIt(RequestContext requestContext) {
        requestContext.setBlocked(true);
        WebHelper.redirect(requestContext.getRequest(), requestContext.getResponse(), redirectUrl);
    }

    /**
     * 校验 IP 访问是否超出限制
     *
     * @param requestContext 请求上下文
     * @return 超出限制则
     */
    private boolean checkIp(RequestContext requestContext) {
        Long ipPeriod = infoKeeper.getIpPeriod(requestContext);
        int ipSessionCreate = infoKeeper.getIpSessionCreate(requestContext);
        int ipVisit = infoKeeper.getIpVisit(requestContext);
        LOGGER.debug("IP {}-{} 访问时间：{} Session创建数：{} 访问次数：{}", requestContext.getIp(),
                requestContext.getSession().getId(), ipPeriod / 1000, ipSessionCreate, ipVisit);
        // 检查 ip 创建Session 数量 或 IP 访问次数 是否超出频率
        if (!checkRate(ipSessionCreateCheckMap, ipPeriod, ipSessionCreate)
                || !checkRate(ipVisitCheckMap, ipPeriod, ipVisit)) {
            LOGGER.info("来自 {} 的用户在 {} 秒内访问了 {} 次， 创建会话 {} 次，触发IP规则，被限制！", requestContext.getIp(), ipPeriod / 1000,
                    ipVisit, ipSessionCreate);
            abuseLocker.lockIP(requestContext, ipBlockTime);
            return false;
        }
        ;
        return true;
    }

    private boolean checkSession(RequestContext requestContext) {
        Long sessionPeriod = infoKeeper.getSessionPeriod(requestContext);
        int sessionVisit = infoKeeper.getSessionVisit(requestContext);
        if (!checkRate(sessionVisitCheckMap, sessionPeriod, sessionVisit)) {
            LOGGER.info("来自 {}-{} 的用户 在 {} 秒内访问了 {} 次， 触发Session规则，被限制！", requestContext.getIp(),
                    requestContext.getSession().getId(), sessionPeriod / 1000, sessionVisit);
            abuseLocker.lockSession(requestContext, sessionBlockTime);
            return false;
        }
        return true;
    }

    private boolean checkRate(Map<Integer, Long> map, Long period, int times) {
        if (MapUtils.isNotEmpty(map)) {
            Long minSeconds = map.get(times);
            if (null != minSeconds && period < minSeconds * 1000) {
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String> getSupports() {
        return ImmutableList.of(TagConstants.NORMAL);
    }

    public void setInfoKeeper(AbuseInfoKeeper infoKeeper) {
        this.infoKeeper = infoKeeper;
    }

    public void setAbuseLocker(AbuseLocker abuseLocker) {
        this.abuseLocker = abuseLocker;
    }

    public void setIpVisitCheckMap(Map<Integer, Long> ipVisitCheckMap) {
        this.ipVisitCheckMap = ipVisitCheckMap;
    }

    public void setIpSessionCreateCheckMap(Map<Integer, Long> ipSessionCreateCheckMap) {
        this.ipSessionCreateCheckMap = ipSessionCreateCheckMap;
    }

    public void setSessionVisitCheckMap(Map<Integer, Long> sessionVisitCheckMap) {
        this.sessionVisitCheckMap = sessionVisitCheckMap;
    }

    public void setIpBlockTime(int ipBlockTime) {
        this.ipBlockTime = ipBlockTime;
    }

    public void setSessionBlockTime(int sessionBlockTime) {
        this.sessionBlockTime = sessionBlockTime;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
