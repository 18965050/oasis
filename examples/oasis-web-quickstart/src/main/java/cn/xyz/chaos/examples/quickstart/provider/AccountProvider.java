package cn.xyz.chaos.examples.quickstart.provider;

import cn.xyz.chaos.examples.quickstart.entity.Account;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

/**
 * AccountProvider <br/>
 * Account服务接口
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年6月24日 下午5:28:43
 * @author lcg
 */
public interface AccountProvider {

    /**
     * 新增一个用户
     *
     * @param account
     * @return
     */
    Account add(Account account);

    /**
     * 获得email代表的账户
     *
     * @param email email
     * @return 邮箱对应的账户，否则为null
     */
    Account getByEmail(String email);

    /**
     * 获得ID代表的账户
     *
     * @param id id
     * @return ID对应的账户，否则为null
     */
    Account getById(Long id);

    /**
     * 是否存在email对应的账户
     *
     * @param email email
     * @return true/false
     */
    boolean isEmailExist(String email);

    /**
     * 获取分页的账户列表
     *
     * @param pageBounds 分页条件
     * @return 符合pageBounds的账户列表。如果不存在则为Empty List。
     */
    PageList<Account> getPagedAccounts(PageBounds pageBounds);

}
