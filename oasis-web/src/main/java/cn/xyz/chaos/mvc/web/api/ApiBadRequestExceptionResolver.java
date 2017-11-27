/**
 * Author lvchenggang
 * XYZ Reserved
 * Created on 2016年6月29日 上午10:07:54
 */
package cn.xyz.chaos.mvc.web.api;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

import com.fasterxml.jackson.databind.ObjectMapper;

import cn.xyz.chaos.common.utils.SpringContextUtils;

/**
 * <pre>
 * API异步(ajax)请求的错误请求异常处理类.
 * 已作废,被{@link ApiGenericHandlerExceptionResolver}取代
 * </pre>
 * 
 * @author lvchenggang
 * @see ApiGenericHandlerExceptionResolver
 */
@Deprecated
public class ApiBadRequestExceptionResolver extends AbstractHandlerExceptionResolver implements InitializingBean {

    private ObjectMapper objectMapper;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        if (ex instanceof HttpMessageNotReadableException) {
            return handleEx(response, BaseResponseDTO.DEFAULT_RESPONSE_RESULT.DATA_MALFORMAT.value(), ex);
        } else if (ex instanceof HttpRequestMethodNotSupportedException
                || ex instanceof NoSuchRequestHandlingMethodException) {
            return handleEx(response, BaseResponseDTO.DEFAULT_RESPONSE_RESULT.REQMETHOD_ERROR.value(), ex);
        } else if (ex instanceof TypeMismatchException) {
            return handleEx(response, BaseResponseDTO.DEFAULT_RESPONSE_RESULT.TYPE_MISMATCH.value(), ex);
        } else if (ex instanceof MissingServletRequestParameterException) {
            return handleEx(response, BaseResponseDTO.DEFAULT_RESPONSE_RESULT.MISS_REQUEST_PARAM.value(), ex);
        } else {
            return null; // 当return为null时会交由其他ExceptionResolver处理
        }
    }

    private ModelAndView handleEx(HttpServletResponse response, Integer errCode, Exception ex) {
        BaseResponseDTO<Void> dto = new BaseResponseDTO<Void>();
        dto.setRet(errCode);
        dto.addError(ex.getMessage());
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

    @Override
    public void afterPropertiesSet() throws Exception {
        this.setOrder(-1);
        objectMapper = SpringContextUtils.getBean("jacksonObjectMapper");
    }

}
