package cn.xyz.chaos.examples.demo.web.svo;

import java.util.List;

import cn.xyz.chaos.orm.mybatis.easylist.list.base.SearchType;
import cn.xyz.chaos.orm.mybatis.easylist.list.base.SortType;
import cn.xyz.chaos.orm.mybatis.easylist.list.base.annotation.SearchItem;
import cn.xyz.chaos.orm.mybatis.easylist.list.base.annotation.SortItem;

/**
 * ProdlistSVO <br/>
 * 搜索值对象
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年8月13日 下午2:18:19
 * @author lcg
 */
public class ProdlistSVO {

    private List<Integer> skuOptIds;

    @SearchItem(value = "p.status", searchType = SearchType.NUMBER_EQUAL)
    private Integer status = 1;

    @SortItem(value = "weight")
    private String weightSort = SortType.DESC.toString();

    private Integer limit = 10;

    private Integer page = 1;

    /**
     * @return the skuOptIds
     */
    public List<Integer> getSkuOptIds() {
        return skuOptIds;
    }

    /**
     * @param skuOptIds the skuOptIds to set
     */
    public void setSkuOptIds(List<Integer> skuOptIds) {
        this.skuOptIds = skuOptIds;
    }

    /**
     * @return the limit
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * @return the page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(Integer page) {
        this.page = page;
    }

}
