package cn.xyz.chaos.examples.showcase.provider.impl.acc;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xyz.chaos.examples.showcase.entity.model.UserModel;
import cn.xyz.chaos.examples.showcase.provider.UserProvider;
import cn.xyz.chaos.examples.showcase.repository.dao.UserDAO;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

/**
 * UserProviderImpl <br/>
 * 用户服务实现
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年7月29日 下午2:34:40
 * @author lcg
 */
@Service
public class UserProviderImpl implements UserProvider {

    @Autowired
    private UserDAO userDAO;

    @Override
    public PageList<UserModel> list(RowBounds rowBounds) {
        if (null == rowBounds) {
            rowBounds = new PageBounds(10, 0);
        }
        PageList<UserModel> list = userDAO.list(rowBounds);
        return list;
    }

}
