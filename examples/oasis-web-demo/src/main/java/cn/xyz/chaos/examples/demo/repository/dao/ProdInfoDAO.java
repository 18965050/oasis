package cn.xyz.chaos.examples.demo.repository.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import cn.xyz.chaos.orm.mybatis.MyBatisRepository;

import cn.xyz.chaos.examples.demo.entity.ProdInfo;
import cn.xyz.chaos.examples.demo.entity.Product;
import cn.xyz.chaos.examples.demo.repository.mapper.ProdInfoMapper;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageBounds;
import cn.xyz.chaos.orm.mybatis.easylist.paginator.domain.PageList;

@MyBatisRepository
public interface ProdInfoDAO extends ProdInfoMapper {

    /**
     * @param optIds TODO
     * @param pageBounds
     * @return
     */
    PageList<ProdInfo> listWithSKU(@Param("optIds") List<Integer> optIds, PageBounds pageBounds);

    PageList<Product> selectBySelectiveWithRowbounds(@Param("prodInfo") ProdInfo prodInfo, PageBounds pageBounds);

    Product selectById(Integer prodId);
}
