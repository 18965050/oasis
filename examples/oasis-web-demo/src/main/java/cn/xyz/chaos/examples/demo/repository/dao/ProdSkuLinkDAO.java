package cn.xyz.chaos.examples.demo.repository.dao;

import java.util.List;

import cn.xyz.chaos.orm.mybatis.MyBatisRepository;

import cn.xyz.chaos.examples.demo.entity.ProdAttrLink;
import cn.xyz.chaos.examples.demo.repository.mapper.ProdSkuLinkMapper;

@MyBatisRepository
public interface ProdSkuLinkDAO extends ProdSkuLinkMapper {

    List<ProdAttrLink> getProdAttrs(Integer prodId);

    void deleteByTagId(Integer tagId);
}
