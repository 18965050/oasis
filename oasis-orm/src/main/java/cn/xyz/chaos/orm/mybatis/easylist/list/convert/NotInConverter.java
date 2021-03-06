/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package cn.xyz.chaos.orm.mybatis.easylist.list.convert;

import java.util.ArrayList;
import java.util.List;

import cn.xyz.chaos.orm.mybatis.easylist.list.criteria.SearchData;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.WhereCriteria;

/**
 * @author zhangxu
 */
@SuppressWarnings("unchecked")
class NotInConverter extends AbstractSearchDataConverter {

    @Override
    public List<Object> convert(Object source) {
        List<Object> result = new ArrayList<Object>();

        String data = null == source ? "" : source.toString();

        for (String subValue : data.split(",")) {
            result.add(subValue);
        }

        return result;
    }

    @Override
    protected WhereCriteria.Criterion buildCriteria(WhereCriteria.Criteria targetCriteria, SearchData data) {
        List<Object> result = data.getData();

        if (isEmpty(result)) {
            return null;
        }

        return targetCriteria.addNotInCriterion(data.getColumn(), result);
    }
}
