/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package cn.xyz.chaos.orm.mybatis.easylist.list.convert;

import cn.xyz.chaos.orm.mybatis.easylist.list.criteria.SearchData;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.WhereCriteria;

/**
 * @author zhangxu
 */
class TextNotLikeConverter extends AbstractSearchDataConverter {
    @Override
    @SuppressWarnings("unchecked")
    public String convert(Object source) {
        return null == source ? "" : source.toString();
    }

    @Override
    protected WhereCriteria.Criterion buildCriteria(WhereCriteria.Criteria targetCriteria, SearchData data) {
        if (isBlank((String) data.getData())) {
            return null;
        }
        // 对统配符进行转义
        data.setData(replace((String) data.getData(), "%", "\\%"));
        data.setData(replace((String) data.getData(), "_", "\\_"));
        return targetCriteria.addNotLikeCriterion("upper(" + data.getColumn() + ")",
                "%" + data.getData().toString().trim().toUpperCase() + "%");
    }
}
