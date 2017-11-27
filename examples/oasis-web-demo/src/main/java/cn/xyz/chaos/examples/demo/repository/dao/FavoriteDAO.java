package cn.xyz.chaos.examples.demo.repository.dao;

import java.util.List;

import cn.xyz.chaos.orm.mybatis.MyBatisRepository;

import cn.xyz.chaos.examples.demo.entity.FavoriteProd;
import cn.xyz.chaos.examples.demo.repository.mapper.FavoriteMapper;

@MyBatisRepository
public interface FavoriteDAO extends FavoriteMapper {

    List<FavoriteProd> selectByUserId(Integer userId);
}
