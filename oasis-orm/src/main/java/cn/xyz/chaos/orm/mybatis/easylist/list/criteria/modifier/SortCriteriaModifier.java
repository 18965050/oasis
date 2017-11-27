package cn.xyz.chaos.orm.mybatis.easylist.list.criteria.modifier;

import java.util.List;

import cn.xyz.chaos.orm.mybatis.easylist.list.base.ISortItem;

/**
 * SortCriteriaModifier <br/>
 * 排序条件修正接口
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年7月9日 下午5:03:41
 * @author lcg
 */
public interface SortCriteriaModifier {

    /**
     * 在组装好sortData之后做点什么
     *
     * @param sortDatas
     */
    public abstract void afterSortDataAssembled(List<ISortItem> sortDatas);

    /**
     * 在组装好sortCriteria之后做点什么
     *
     * @param sortCriteria
     * @return
     */
    public abstract String afterSorCriteriaAssembled(String sortCriteria);

}
