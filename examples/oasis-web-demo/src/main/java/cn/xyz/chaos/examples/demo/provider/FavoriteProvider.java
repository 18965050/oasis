package cn.xyz.chaos.examples.demo.provider;

import java.util.List;

import cn.xyz.chaos.examples.demo.provider.dto.FavoriteDTO;

public interface FavoriteProvider {

    List<FavoriteDTO> selectBySelective(FavoriteDTO favoriteDTO);

    void add(FavoriteDTO favoriteDTO);

    List<FavoriteDTO> getByUserId(int userId);

}
