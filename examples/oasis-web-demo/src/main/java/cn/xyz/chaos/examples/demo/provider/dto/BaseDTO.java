package cn.xyz.chaos.examples.demo.provider.dto;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基础DTO类
 *
 * @author lvchenggang
 *
 */
public class BaseDTO {

    /**
     * 分页参数，第几页
     */
    private int pageIndex = 1;

    /**
     * 分页参数，每页多少条
     */
    private int limit = 10;

    private Map<String, String> errors = new LinkedHashMap<String, String>();

    public Map<String, String> getErrors() {
        return errors;
    }

    public void addError(String key, String value) {
        this.errors.put(key, value);
    }

    public void cleanErrors() {
        this.errors.clear();
    }

    public boolean hasError() {
        return !this.errors.isEmpty();
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
