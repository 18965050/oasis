package cn.xyz.chaos.common.log;

import ch.qos.logback.core.PropertyDefinerBase;

/**
 * <pre>
 * 日志属性文件定义器.返回环境依赖的日志属性文件路径.
 * 需要确保属性文件引入使用&lt;property resource=... /&gt;的方式
 * </pre>
 *
 * @author lvchenggang
 * @date 2017年9月7日 下午12:16:09
 * 
 */

public class FilePropertyDefiner extends PropertyDefinerBase {

    @Override
    public String getPropertyValue() {
        String currentProfile = System.getProperty("spring.profiles.active", "development");
        return "properties/logback-" + currentProfile + ".properties";
    }

}
