/**
 * Author lvchenggang
 * XYZ Reserved
 * Created on 2016年5月14日 下午2:44:10
 */
package cn.xyz.chaos.mvc.web.api.druid;

import java.io.IOException;
import java.io.Writer;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.druid.stat.DruidStatService;
import com.alibaba.druid.support.http.StatViewServlet;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.xyz.chaos.common.utils.Profiles;
import cn.xyz.chaos.common.utils.SpringContextUtils;
import cn.xyz.chaos.mvc.web.api.BaseResponseDTO;

/**
 * @author lvchenggang
 *
 */
@SuppressWarnings("serial")
public class EnvironmentStatViewServlet extends StatViewServlet {

    private static final Pattern JMXURL_PATTERN = Pattern.compile("^/webapp|^/weburi|^/websession|^/spring");

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String environment = System.getProperty(Profiles.ACTIVE_PROFILE, Profiles.PRODUCTION);
        if (environment.equals(Profiles.PREVIEW) || environment.equals(Profiles.PRODUCTION)) {
            BaseResponseDTO dto = new BaseResponseDTO();
            dto.setRet(BaseResponseDTO.DEFAULT_RESPONSE_RESULT.RESOURCE_NOT_EXIST.value());
            dto.addError(BaseResponseDTO.DEFAULT_RESPONSE_RESULT.RESOURCE_NOT_EXIST.desc());
            response.setContentType("text/html;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            Writer writer = response.getWriter();
            ObjectMapper objectMapper = SpringContextUtils.getBean(ObjectMapper.class);
            writer.write(objectMapper.writeValueAsString(dto));
            writer.flush();
        } else {
            super.service(request, response);
        }
    }

    @Override
    protected String process(String url) {
        if (JMXURL_PATTERN.matcher(url).find()) {
            return DruidStatService.getInstance().service(url);
        } else {
            return super.process(url);
        }

    }

}
