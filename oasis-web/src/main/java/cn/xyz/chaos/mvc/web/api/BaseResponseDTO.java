package cn.xyz.chaos.mvc.web.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseResponseDTO<T> {

    private Integer ret = DEFAULT_RESPONSE_RESULT.SUCCESS.value();

    private final List<String> msg = new ArrayList<String>();

    private T data;

    /**
     * 默认构造函数
     */
    public BaseResponseDTO() {

    }

    public BaseResponseDTO(T dto) {
        this.data = dto;
    }

    /**
     * @return the ret
     */
    public Integer getRet() {
        return ret;
    }

    /**
     * @param ret the ret to set
     */
    public void setRet(Integer ret) {
        this.ret = ret;
    }

    public void addError(String error) {
        this.msg.add(error);
    }

    public void addErrors(String[] errors) {
        this.addErrors(Arrays.asList(errors));
    }

    public void addErrors(List<String> errorList) {
        this.msg.addAll(errorList);
    }

    public void removeError(String error) {
        this.msg.remove(error);
    }

    /**
     * @return the msg
     */
    public List<String> getMsg() {
        return msg;
    }

    /**
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(T data) {
        this.data = data;
    }

    public static enum DEFAULT_RESPONSE_RESULT {
        SUCCESS(0, "[]"), // 成功
        AUTHEN_FAIL(-1, "认证失败"), // 认证失败
        AUTHOR_FAIL(-2, "权限不足"), // 授权不足
        PARAM_CHECK_FAIL(-3, ""), // 参数校验失败,错误信息交由业务逻辑处理
        RESOURCE_NOT_EXIST(-4, "请求资源不存在"), // 请求资源不存在
        SYSTEM_ERROR(-5, "系统错误"),
        DATA_MALFORMAT(-6, "请求参数数据格式不正确"),
        REQMETHOD_ERROR(-7, "请求方法不正确"),
        TYPE_MISMATCH(-8, "请求参数类型不匹配"),
        MISS_REQUEST_PARAM(-9, "请求参数缺失");

        private final Integer value;

        private final String desc;

        private DEFAULT_RESPONSE_RESULT(int value, String desc) {
            this.value = value;
            this.desc = desc;
        }

        public int value() {
            return value;
        }

        public String desc() {
            return desc;
        }
    }

}
