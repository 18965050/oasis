package cn.xyz.chaos.mvc.web.api;

import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.xyz.chaos.common.utils.SpringContextUtils;
import cn.xyz.chaos.mvc.shiro.annotation.RequiresAuthc;

/**
 * cn.xyz.chaos.mvc.shiro.spring
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2014/9/1 18:26.
 */
public class RequiresAuthcAnnotationInterceptor extends HandlerInterceptorAdapter implements InitializingBean {

    private ObjectMapper objectMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            boolean authenticated = false;
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            RequiresAuthc authClazz = handlerMethod.getBeanType().getAnnotation(RequiresAuthc.class);
            RequiresAuthc authMethod = handlerMethod.getMethodAnnotation(RequiresAuthc.class);
            if (null != authMethod) {
                authenticated = authMethod.authenticated();
            } else if (null != authClazz) {
                authenticated = authClazz.authenticated();
            }

            Subject subject = SecurityUtils.getSubject();
            boolean authenticated0 = (null != subject) && subject.isAuthenticated();

            if (authenticated && !authenticated0) {
                BaseResponseDTO<Void> dto = new BaseResponseDTO<Void>();
                dto.setRet(BaseResponseDTO.DEFAULT_RESPONSE_RESULT.AUTHEN_FAIL.value());
                dto.addError(BaseResponseDTO.DEFAULT_RESPONSE_RESULT.AUTHEN_FAIL.desc());
                response.setContentType("application/json;charset=UTF-8");
                response.setStatus(HttpServletResponse.SC_OK);
                Writer writer = response.getWriter();
                writer.write(objectMapper.writeValueAsString(dto));
                writer.flush();
                return false;
            }
        }

        return super.preHandle(request, response, handler);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        objectMapper = SpringContextUtils.getBean("jacksonObjectMapper");
    }

}
