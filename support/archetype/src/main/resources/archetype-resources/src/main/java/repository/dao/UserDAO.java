#set($symbol_pound='#')#set($symbol_dollar='$')#set($symbol_escape='\')package ${package}.repository.dao;

import ${package}.repository.mapper.UserMapper;
import org.apache.ibatis.session.RowBounds;
import cn.xyz.chaos.orm.mybatis.MyBatisRepository;

import ${package}.entity.model.UserModel;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

@MyBatisRepository
public interface UserDAO extends UserMapper {

    PageList<UserModel> list(RowBounds pageBounds);

}
