package cn.xyz.chaos.orm.mybatis.easylist.list.convert;

import java.util.Date;

import cn.xyz.chaos.orm.mybatis.easylist.list.criteria.SearchData;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.WhereCriteria.Criteria;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.WhereCriteria.Criterion;

public class DateGtConverter extends AbstractDateConverter {

    @Override
    public Date convert(Object source) {
        if (null == source || !(source instanceof Date)) {
            return null;
        }
        return (Date) source;
    }

    @Override
    protected Criterion buildCriteria(Criteria targetCriteria, SearchData data) {
        Date result = data.getData();

        if (null == result) {
            return null;
        }
        return targetCriteria.addGreaterThanCriterion(data.getColumn(), result);
    }

}
