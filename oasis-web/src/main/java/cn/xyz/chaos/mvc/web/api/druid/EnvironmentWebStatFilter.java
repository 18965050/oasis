/**
 * Author lvchenggang
 * XYZ Reserved
 * Created on 2016年5月14日 下午3:23:19
 */
package cn.xyz.chaos.mvc.web.api.druid;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.alibaba.druid.support.http.WebStatFilter;

import cn.xyz.chaos.common.utils.Profiles;

/**
 * @author lvchenggang
 *
 */
public class EnvironmentWebStatFilter extends WebStatFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String environment = System.getProperty(Profiles.ACTIVE_PROFILE, Profiles.PRODUCTION);
        if (environment.equals(Profiles.PREVIEW) || environment.equals(Profiles.PRODUCTION)) {
            chain.doFilter(request, response);
        } else {
            super.doFilter(request, response, chain);
        }
    }

}
