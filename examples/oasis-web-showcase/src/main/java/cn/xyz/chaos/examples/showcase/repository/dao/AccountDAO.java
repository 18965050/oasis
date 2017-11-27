package cn.xyz.chaos.examples.showcase.repository.dao;

import cn.xyz.chaos.orm.mybatis.MyBatisRepository;

import cn.xyz.chaos.examples.showcase.entity.Account;
import cn.xyz.chaos.examples.showcase.repository.mapper.AccountMapper;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

@MyBatisRepository
public interface AccountDAO extends AccountMapper {

    /**
     * 分页查询所有
     *
     * @param pageBounds
     * @return
     */
    PageList<Account> selectAll(PageBounds pageBounds);
}
