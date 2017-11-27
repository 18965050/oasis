package cn.xyz.chaos.common.cors;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 跨域过滤器
 * @author Zhang Peng
 * @date 2017/7/12 11:55
 */
public class CorsFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(CorsFilter.class);

    private String regex;
    private String headerKey;
    private final String ORIGIN_KEY = "Origin";

    public void init(FilterConfig filterConfig) throws ServletException {
        // 取配置参数
        regex = filterConfig.getInitParameter("regex");
        headerKey = filterConfig.getInitParameter("headerKey");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        if (StringUtils.isBlank(regex) || StringUtils.isBlank(headerKey)) {
            throw new ServletException("读取跨域过滤器的配置参数失败");
        }

        // 读取请求地址的域
        String domain = httpRequest.getHeader(headerKey);
        String origin = httpRequest.getHeader(ORIGIN_KEY);
        if (StringUtils.isBlank(domain) || StringUtils.isBlank(origin)
            || origin.toLowerCase().contains(domain.toLowerCase())) {
            logger.debug("domain={}, origin={}, 跳过检查", domain, origin);
            chain.doFilter(httpRequest, httpResponse);
            return;
        }

        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(origin).matches()) {
            logger.warn("客户端域 origin={} 不在跨域白名单中", origin);
            httpResponse.sendError(403, "客户端域不在跨域白名单中");
            throw new ServletException("客户端域不在跨域白名单中");
        }

        logger.debug("对 origin={} 放开跨域限制", origin);
        if (StringUtils.isBlank(httpRequest.getHeader("Access-Control-Allow-Origin"))) {
            httpResponse.addHeader("Access-Control-Allow-Origin", origin);
            httpResponse.addHeader("Access-Control-Allow-Credentials", "true");
            httpResponse.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, OPTIONS, DELETE");
            httpResponse.addHeader("Access-Control-Allow-Headers",
                "DNT, X-CustomHeader, Keep-Alive, User-Agent, X-Requested-With, If-Modified-Since,"
                    + " Cache-Control, Content-Type, Content-Range, Range");
            httpResponse.addHeader("Access-Control-Expose-Headers",
                "DNT, X-CustomHeader, Keep-Alive, User-Agent, X-Requested-With, If-Modified-Since,"
                    + " Cache-Control, Content-Type, Content-Range, Range");
        }

        if (httpRequest.getMethod().equals("OPTIONS")) {
            httpResponse.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        chain.doFilter(httpRequest, httpResponse);
    }

    public void destroy() {

    }
}
