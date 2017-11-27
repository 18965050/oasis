package cn.xyz.chaos.common.page;

import java.io.Serializable;

/**
 * Created by lcg on 2015/8/26.
 */
public class PageReq implements Serializable {
    public static final int DEFAULT_PAGE_INDEX = 1;
    public static final int DEFAULT_PAGE_SIZE = 20;

    private int pageIndex;
    private int pageSize;

    public PageReq() {
        this(DEFAULT_PAGE_INDEX, DEFAULT_PAGE_SIZE);
    }

    public PageReq(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
