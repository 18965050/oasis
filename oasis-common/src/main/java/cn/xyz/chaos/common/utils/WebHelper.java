package cn.xyz.chaos.common.utils;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;

/**
 * cn.xyz.chaos.common.utils web 帮助类
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/1/30 16:15.
 */
public class WebHelper {

    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(WebHelper.class);

    /**
     * 客户端重定向
     *
     * @param request 请求
     * @param response 响应
     * @param url 重定向url
     */
    public static void redirect(HttpServletRequest request, HttpServletResponse response, String url) {
        try {
            if (isAjax(request)) {
                response.getWriter().write(new StringBuilder("<script>window.location.href = '").append(url)
                        .append("<script>").toString());

            } else {
                response.sendRedirect(url);
            }
        } catch (IOException e) {
            // ignore
            logger.error("重定向异常:", e);
        }
    }

    public static boolean isAjax(HttpServletRequest request) {
        String header = request.getHeader("X-Requested-With");
        return "XMLHttpRequest".equalsIgnoreCase(header);
    }

}
