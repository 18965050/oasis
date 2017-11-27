/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package cn.xyz.chaos.orm.mybatis.easylist.list.convert;

import cn.xyz.chaos.orm.mybatis.easylist.list.criteria.SearchData;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.WhereCriteria;

/**
 * @author zhangxu
 */
class NumberNotEqualConverter extends AbstractNumberConverter {
    @Override
    protected WhereCriteria.Criterion buildCriteria(WhereCriteria.Criteria targetCriteria, SearchData data) {
        return targetCriteria.addNotEqualToCriterion(data.getColumn(), data.getData());
    }
}
