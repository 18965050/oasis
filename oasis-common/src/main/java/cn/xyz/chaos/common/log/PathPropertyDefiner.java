package cn.xyz.chaos.common.log;

import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import ch.qos.logback.core.PropertyDefinerBase;

/**
 * <pre>
 * logback日志存储路径自定义变量.
 * 这种方式并不好,只支持logPath一个变量的配置,更好的方式为返回一个环境依赖的属性文件,支持自由配置
 * 可参见: {@link FilePropertyDefiner}
 * </pre>
 *
 * @author lvchenggang
 * @date 2017年5月22日 上午9:37:54
 * @see FilePropertyDefiner
 */
@Deprecated
public class PathPropertyDefiner extends PropertyDefinerBase {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getPropertyValue() {
        String logPath = "./log";
        try {
            String currentProfile = System.getProperty("spring.profiles.active", "development");
            String contextFileName = "properties/application-" + currentProfile + ".properties";
            Properties contextProperties = PropertiesLoaderUtils.loadAllProperties(contextFileName);
            logPath = StringUtils.isNotBlank(contextProperties.getProperty("log.path"))
                    ? contextProperties.getProperty("log.path") : logPath;
        } catch (Exception e) {
            System.err.println("logback日志路径初始化失败,将使用默认路径:" + logPath);
            logger.error("logback日志路径初始化失败", e);
        }
        return logPath;
    }

}
