package cn.xyz.chaos.mvc.web.api;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.xyz.chaos.common.json.IOObjectMapper;
import cn.xyz.chaos.common.utils.SpringContextUtils;
import cn.xyz.chaos.mvc.web.api.BaseResponseDTO.DEFAULT_RESPONSE_RESULT;

/**
 * <pre>
 * 通用API异步(ajax)请求的错误处理类.
 * json格式返回BaseResponseDTO对象中定义的相关异常
 * </pre>
 *
 * @author lvchenggang
 * @date 2017年9月5日 下午1:53:41
 * @see BaseResponseDTO
 */
public class ApiGenericHandlerExceptionResolver extends AbstractHandlerExceptionResolver implements InitializingBean {

    private ObjectMapper objectMapper;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public void afterPropertiesSet() throws Exception {
        this.setOrder(-1);
        try {
            objectMapper = SpringContextUtils.getBean(ObjectMapper.class);
        } catch (BeansException e) {
            log.warn("Spring中未配置ObjectMapper Bean,将独立创建之");
            objectMapper = new IOObjectMapper();
        }

    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        if (ex instanceof HttpMessageNotReadableException) {
            return handleEx(response, BaseResponseDTO.DEFAULT_RESPONSE_RESULT.DATA_MALFORMAT, ex);
        } else if (ex instanceof HttpRequestMethodNotSupportedException
                || ex instanceof NoSuchRequestHandlingMethodException) {
            return handleEx(response, BaseResponseDTO.DEFAULT_RESPONSE_RESULT.REQMETHOD_ERROR, ex);
        } else if (ex instanceof TypeMismatchException) {
            return handleEx(response, BaseResponseDTO.DEFAULT_RESPONSE_RESULT.TYPE_MISMATCH, ex);
        } else if (ex instanceof MissingServletRequestParameterException) {
            return handleEx(response, BaseResponseDTO.DEFAULT_RESPONSE_RESULT.MISS_REQUEST_PARAM, ex);
        } else if (ex instanceof AuthorizationException) {
            return handleEx(response, BaseResponseDTO.DEFAULT_RESPONSE_RESULT.AUTHOR_FAIL, ex);
        } else {
            return handleEx(response, BaseResponseDTO.DEFAULT_RESPONSE_RESULT.SYSTEM_ERROR, ex);
        }
    }

    private ModelAndView handleEx(HttpServletResponse response, DEFAULT_RESPONSE_RESULT errEnum, Exception ex) {
        BaseResponseDTO<Void> dto = new BaseResponseDTO<Void>();
        this.log.error("错误信息:" + ex.getMessage());
        dto.setRet(errEnum.value());
        dto.addError(errEnum.desc());
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        try {
            Writer writer = response.getWriter();
            writer.write(objectMapper.writeValueAsString(dto));
            writer.flush();
        } catch (IOException e) {
            log.error("异常输出处理失败", e);
        } finally {
            return new ModelAndView();
        }
    }

}
