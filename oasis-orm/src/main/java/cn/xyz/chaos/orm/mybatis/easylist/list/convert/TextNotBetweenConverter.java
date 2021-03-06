/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package cn.xyz.chaos.orm.mybatis.easylist.list.convert;

import cn.xyz.chaos.orm.mybatis.easylist.list.criteria.SearchData;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.WhereCriteria;

/**
 * @author zhangxu
 */
@SuppressWarnings("unchecked")
class TextNotBetweenConverter extends AbstractSearchDataConverter {

    @Override
    public String[] convert(Object source) {
        String[] result = new String[2];

        if (null != source) {
            String[] data = source.toString().split(",");
            result[0] = data[0];
            result[0] = data[1];
        }

        return result;
    }

    @Override
    protected WhereCriteria.Criterion buildCriteria(WhereCriteria.Criteria targetCriteria, SearchData data) {
        String[] result = data.getData();

        if (isEmpty(result)) {
            return null;
        }

        return targetCriteria.addNotBetweenCriterion(data.getColumn(), result);
    }
}
