/**
 * Author lvchenggang
 * XYZ Reserved
 * Created on 2016年4月15日 下午3:35:32
 */
package cn.xyz.chaos.mvc.web.api;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.resource.DefaultServletHttpRequestHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author lvchenggang
 *
 */
public class ApiDefaultServletHttpRequestHandler extends DefaultServletHttpRequestHandler {

    @Autowired
    private ObjectMapper objectMapper;

    private List<String> excludePrefixList;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String requestURI = request.getRequestURI();
        boolean handleDefault = false;
        if (CollectionUtils.isNotEmpty(excludePrefixList)) {
            for (String excludePrefix : excludePrefixList) {
                if (requestURI.startsWith(excludePrefix)) {
                    handleDefault = true;
                }
            }
        }
        if (handleDefault) {
            super.handleRequest(request, response);
        } else {
            BaseResponseDTO<Void> dto = new BaseResponseDTO<Void>();
            dto.setRet(BaseResponseDTO.DEFAULT_RESPONSE_RESULT.RESOURCE_NOT_EXIST.value());
            dto.addError(BaseResponseDTO.DEFAULT_RESPONSE_RESULT.RESOURCE_NOT_EXIST.desc());
            response.setContentType("text/html;charset=UTF-8");
            response.setStatus(HttpServletResponse.SC_OK);
            Writer writer = response.getWriter();
            writer.write(objectMapper.writeValueAsString(dto));
            writer.flush();
        }
    }

    /**
     * @return the excludePrefixList
     */
    public List<String> getExcludePrefixList() {
        return excludePrefixList;
    }

    /**
     * @param excludePrefixList the excludePrefixList to set
     */
    public void setExcludePrefixList(List<String> excludePrefixList) {
        this.excludePrefixList = excludePrefixList;
    }
}
