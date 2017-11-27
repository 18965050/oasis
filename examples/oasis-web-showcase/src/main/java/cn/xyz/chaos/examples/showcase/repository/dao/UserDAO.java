package cn.xyz.chaos.examples.showcase.repository.dao;

import org.apache.ibatis.session.RowBounds;
import cn.xyz.chaos.orm.mybatis.MyBatisRepository;

import cn.xyz.chaos.examples.showcase.entity.model.UserModel;
import cn.xyz.chaos.examples.showcase.repository.mapper.UserMapper;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

@MyBatisRepository
public interface UserDAO extends UserMapper {

    PageList<UserModel> list(RowBounds pageBounds);

}
