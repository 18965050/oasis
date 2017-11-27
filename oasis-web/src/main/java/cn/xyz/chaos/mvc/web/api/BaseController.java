package cn.xyz.chaos.mvc.web.api;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.validation.ObjectError;

/**
 * 基础Controller
 * @author lvchenggang
 *
 */
public abstract class BaseController extends BaseLogComponent {

    /**
     * 成功情况下的对象返回
     * @param t
     * @return
     */
    protected <T> BaseResponseDTO<T> returnWithSuccess(T t) {
        return new BaseResponseDTO<T>(t);
    }

    /**
     * 失败情况下的对象返回
     * @param t
     * @param errorCode
     * @param errorMsg
     * @return
     */
    protected BaseResponseDTO returnWithFail(Integer errorCode, List<String> errorMsg) {
        BaseResponseDTO dto = new BaseResponseDTO();
        dto.setRet(errorCode);
        dto.getMsg().addAll(errorMsg);
        return dto;
    }

    /**
     * 参数校验失败情况下, 多错误对象返回
     * @param errorList
     * @return
     */
    protected BaseResponseDTO returnWithCheckFail(List<? extends ObjectError> errorList) {
        BaseResponseDTO dto = new BaseResponseDTO();
        if (CollectionUtils.isNotEmpty(errorList)) {
            dto.setRet(BaseResponseDTO.DEFAULT_RESPONSE_RESULT.PARAM_CHECK_FAIL.value());
            for (ObjectError error : errorList) {
                dto.getMsg().add(error.getDefaultMessage());
            }
        }
        return dto;
    }

    /**
     * 参数校验失败情况下, 单错误对象返回
     * @param error
     * @return
     */
    protected BaseResponseDTO returnWithCheckFail(ObjectError error) {
        BaseResponseDTO dto = new BaseResponseDTO();
        dto.setRet(BaseResponseDTO.DEFAULT_RESPONSE_RESULT.PARAM_CHECK_FAIL.value());
        dto.getMsg().add(error.getDefaultMessage());
        return dto;
    }

}
