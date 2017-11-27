package cn.xyz.chaos.examples.quickstart.repository.dao;

import cn.xyz.chaos.examples.quickstart.repository.mapper.UserMapper;
import org.apache.ibatis.session.RowBounds;
import cn.xyz.chaos.orm.mybatis.MyBatisRepository;

import cn.xyz.chaos.examples.quickstart.entity.model.UserModel;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

@MyBatisRepository
public interface UserDAO extends UserMapper {

    PageList<UserModel> list(RowBounds pageBounds);

}
