#set($symbol_pound='#')#set($symbol_dollar='$')#set($symbol_escape='\')package ${package}.web.service.acc;

import java.util.Date;
import java.util.Map;

import ${package}.entity.Account;import ${package}.repository.dao.AccountDAO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import ${package}.entity.User;import ${package}.entity.model.UserModel;import ${package}.repository.dao.UserDAO;import ${package}.web.dvo.UserDVO;
import cn.xyz.chaos.orm.mybatis.easylist.list.EasyListHelper;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * UserService <br/>
 * 前端Service
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年7月29日 下午2:31:05
 * @author lcg
 */
@Service
public class UserService {

    public static final String PASSWORD = "1234";
    @Autowired
    private UserDAO userDAO;

    @Autowired
    private AccountDAO accountDAO;

    /**
     * @param userDVO
     * @return
     */
    public Map<String, Object> list(UserDVO userDVO) {
        PageBounds pageBounds = EasyListHelper.getPageBounds(userDVO, userDVO.getLimit(), userDVO.getPageIndex());
        // 通过下面这种方法，直接调用自动生成的查询语句，可以分页
        // UserCriteria userCriteria = new UserCriteria();
        // PageList<User> rsList = (PageList<User>) userDAO.selectByCriteriaWithRowbounds(userCriteria, pageBounds);

        // 通过这种组合的办法，也可以
        PageList<UserModel> rsList = userDAO.list(pageBounds);

        return ImmutableMap.<String, Object> of("result", "success", "data", rsList, "count",
                rsList.getPaginator().getTotalCount());
    }

    /**
     * @param userDVO
     * @return
     */
    @Transactional
    public Map<String, Object> add(UserDVO userDVO) {
        User user = new User();

        user.setName(userDVO.getName());
        user.setNickName(userDVO.getNickName());
        user.setMobile(userDVO.getMobile());
        user.setEmail(userDVO.getEmail());

        user.setStatus(1);
        Date now = new Date();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        user.setCreateUserId(1L);
        user.setUpdateUserId(1L);

        int insertNum = userDAO.insert(user);
        if (insertNum != 1) {
            throw new RuntimeException("User创建系统异常");
        }
        // 同时添加账户信息，默认密码为 ${symbol_pound}PASSWORD
        Account account = new Account();
        account.setId(user.getId());// id 和 User相同
        account.setEmail(user.getEmail());
        account.setMobile(user.getMobile());
        account.setName(user.getName());
        account.setNickName(user.getNickName());
        account.setStatus(user.getStatus());
        if (StringUtils.isBlank(userDVO.getPassword())) {
            account.setPassword(PASSWORD);
        } else {
            account.setPassword(userDVO.getPassword());
        }

        int insertAccNum = accountDAO.insert(account);
        if (insertAccNum != insertNum) {
            throw new RuntimeException("Account创建系统异常");
        }

        return ImmutableMap.<String, Object> of("result", "success", "id", user.getId(), "insertNum", insertNum);
    }

}
