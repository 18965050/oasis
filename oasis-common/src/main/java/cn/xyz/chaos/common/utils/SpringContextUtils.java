package cn.xyz.chaos.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtils implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;
    private static Logger logger = LoggerFactory.getLogger(SpringContextUtils.class);

    public SpringContextUtils() {
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        logger.debug("注入ApplicationContext到SpringContextUtils:" + applicationContext);
        if (SpringContextUtils.applicationContext != null) {
            logger.warn("SpringContextUtils中的ApplicationContext被覆盖, 原有ApplicationContext为:" + applicationContext);
        }

        SpringContextUtils.applicationContext = applicationContext;
    }

    public void destroy() throws Exception {
        clear();
    }

    public static ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }

    public static <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    public static void clear() {
        logger.debug("清除SpringContextUtils中的ApplicationContext:" + applicationContext);
        applicationContext = null;
    }

    private static void assertContextInjected() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextUtils");
        }
    }
}
