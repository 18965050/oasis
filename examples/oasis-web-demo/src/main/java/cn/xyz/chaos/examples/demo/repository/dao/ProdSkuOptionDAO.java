package cn.xyz.chaos.examples.demo.repository.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import cn.xyz.chaos.orm.mybatis.MyBatisRepository;

import cn.xyz.chaos.examples.demo.entity.Attribute;
import cn.xyz.chaos.examples.demo.repository.mapper.ProdSkuOptionMapper;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

@MyBatisRepository
public interface ProdSkuOptionDAO extends ProdSkuOptionMapper {

    PageList<Attribute> selectBySelectiveWithRowbounds(@Param("attr") Attribute attr, PageBounds pageBounds);

    Attribute selectById(Integer id);

    List<Attribute> selectByTagId(Integer tagId);
}
