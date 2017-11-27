#set($symbol_pound='#')#set($symbol_dollar='$')#set($symbol_escape='\')package ${package}.repository.dao;

import cn.xyz.chaos.orm.mybatis.MyBatisRepository;

import ${package}.entity.Account;import ${package}.repository.mapper.AccountMapper;
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
