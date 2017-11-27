/*
 * Copyright 2011 Focus Technology, Co., Ltd. All rights reserved.
 */
package cn.xyz.chaos.orm.mybatis.easylist.list.base;

/**
 * 排序项
 *
 * @author zhangxu
 */
public interface ISortItem extends ISqlItem {
    SortType getType();

    String toSortSql();
}
