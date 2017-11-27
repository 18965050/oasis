/**
 * Author lvchenggang
 * XYZ Reserved
 * Created on 2016年4月15日 下午2:55:30
 */
package cn.xyz.chaos.mvc.web.api;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.xyz.chaos.common.utils.SpringContextUtils;

/**
 * <pre>
 * API异步(ajax)请求的系统异常处理类.
 * 已作废,被{@link ApiGenericHandlerExceptionResolver}取代
 * </pre>
 * 
 * @author lvchenggang
 * @see ApiGenericHandlerExceptionResolver
 */
@Deprecated
public class ApiHandlerExceptionResolver extends AbstractHandlerExceptionResolver implements InitializingBean {

    private ObjectMapper objectMapper;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        BaseResponseDTO<Void> dto = new BaseResponseDTO<Void>();
        dto.setRet(BaseResponseDTO.DEFAULT_RESPONSE_RESULT.SYSTEM_ERROR.value());
        dto.addError(BaseResponseDTO.DEFAULT_RESPONSE_RESULT.SYSTEM_ERROR.desc());
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            Writer writer = response.getWriter();
            writer.write(objectMapper.writeValueAsString(dto));
            writer.flush();
        } catch (IOException e) {
            log.error("异常输出处理失败", e);
        }

        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.setOrder(Ordered.LOWEST_PRECEDENCE);
        objectMapper = SpringContextUtils.getBean("jacksonObjectMapper");
    }

}
