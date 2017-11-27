package cn.xyz.chaos.examples.showcase.provider.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xyz.chaos.examples.showcase.entity.Account;
import cn.xyz.chaos.examples.showcase.entity.criteria.AccountCriteria;
import cn.xyz.chaos.examples.showcase.provider.AccountProvider;
import cn.xyz.chaos.examples.showcase.repository.dao.AccountDAO;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

import com.google.common.base.Preconditions;

/**
 * AccountProviderImpl <br/>
 * Account服务实现
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年6月24日 下午6:59:07
 * @author lcg
 */
@Service
public class AccountProviderImpl implements AccountProvider {

    @Autowired
    private AccountDAO accountDAO;

    @Override
    public Account add(Account account) {
        Preconditions.checkNotNull(account, "account 不可为null");
        Preconditions.checkNotNull(account.getId(), "account 必须被设置id");
        Preconditions.checkNotNull(accountDAO, "account 必须被设置status");
        accountDAO.insert(account);
        // 服务化中，入参的Account和调用方的已经不是同一个
        return account;
    }

    @Override
    public Account getByEmail(String email) {
        if (StringUtils.isBlank(email)) {
            return null;
        }
        AccountCriteria accountCriteria = new AccountCriteria();
        accountCriteria.or().andEmailEqualTo(email);
        List<Account> selectByExample = accountDAO.selectByCriteria(accountCriteria);
        if (CollectionUtils.isEmpty(selectByExample)) {
            return null;
        }
        return selectByExample.get(0);
    }

    @Override
    public Account getById(Long id) {
        Preconditions.checkNotNull(id, "ID 不可为null");
        return accountDAO.selectByPrimaryKey(id);
    }

    @Override
    public boolean isEmailExist(String email) {
        return null != getByEmail(email);
    }

    @Override
    public PageList<Account> getPagedAccounts(PageBounds pageBounds) {
        return accountDAO.selectAll(pageBounds);
    }

}
