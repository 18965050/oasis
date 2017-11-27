/**
 * Author lvchenggang
 * XYZ Reserved
 * Created on 2016年4月13日 下午5:20:03
 */
package cn.xyz.chaos.orm.mybatis.easylist.list.convert;

import cn.xyz.chaos.orm.mybatis.easylist.list.criteria.SearchData;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.WhereCriteria.Criteria;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.WhereCriteria.Criterion;

/**
 * @author lvchenggang
 *
 */
public class NumberGtConverter extends AbstractNumberConverter {

    @Override
    protected Criterion buildCriteria(Criteria targetCriteria, SearchData data) {
        return targetCriteria.addGreaterThanCriterion(data.getColumn(), data.getData());
    }

}
