package cn.xyz.chaos.examples.demo.repository.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import cn.xyz.chaos.orm.mybatis.MyBatisRepository;

import cn.xyz.chaos.examples.demo.entity.Appraise;
import cn.xyz.chaos.examples.demo.entity.Comment;
import cn.xyz.chaos.examples.demo.repository.mapper.AppraiseMapper;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

@MyBatisRepository
public interface AppraiseDAO extends AppraiseMapper {

    PageList<Comment> selectBySelectiveWithRowbounds(@Param("appraise") Appraise appraise, PageBounds pageBounds);

    List<Comment> selectByProdId(Integer prodId);
}
