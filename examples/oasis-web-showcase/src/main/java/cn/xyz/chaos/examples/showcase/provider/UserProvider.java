package cn.xyz.chaos.examples.showcase.provider;

import org.apache.ibatis.session.RowBounds;

import cn.xyz.chaos.examples.showcase.entity.model.UserModel;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

/**
 * UserProvider <br/>
 * 用户服务接口
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年7月29日 下午2:33:31
 * @author lcg
 */
public interface UserProvider {

    /**
     * 分页列表
     *
     * @param rowBounds
     * @return
     */
    PageList<UserModel> list(RowBounds rowBounds);

}
