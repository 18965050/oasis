package cn.xyz.chaos.examples.demo.provider.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.xyz.chaos.examples.demo.entity.Favorite;
import cn.xyz.chaos.examples.demo.entity.FavoriteProd;
import cn.xyz.chaos.examples.demo.entity.criteria.FavoriteCriteria;
import cn.xyz.chaos.examples.demo.provider.FavoriteProvider;
import cn.xyz.chaos.examples.demo.provider.dto.FavoriteDTO;
import cn.xyz.chaos.examples.demo.repository.dao.FavoriteDAO;

@Repository
public class FavoriteProviderImpl implements FavoriteProvider {

    @Autowired
    private FavoriteDAO favoriteDAO;

    @Autowired
    private Mapper dozerMapper;

    @Override
    public List<FavoriteDTO> selectBySelective(FavoriteDTO favoriteDTO) {
        FavoriteCriteria favoriteCriteria = new FavoriteCriteria();
        FavoriteCriteria.Criteria criteria = favoriteCriteria.or();
        if (favoriteDTO.getFavoriteId() != null) {
            criteria.andFavoriteIdEqualTo(favoriteDTO.getFavoriteId());
        }
        if (favoriteDTO.getProdId() != null) {
            criteria.andProdIdEqualTo(favoriteDTO.getProdId());
        }
        if (favoriteDTO.getUserId() != null) {
            criteria.andUserIdEqualTo(favoriteDTO.getUserId());
        }
        if (favoriteDTO.getAddTime() != null) {
            criteria.andAddTimeEqualTo(favoriteDTO.getAddTime());
        }

        List<Favorite> favoriteList = this.favoriteDAO.selectByCriteria(favoriteCriteria);
        List<FavoriteDTO> favoriteDTOList = new ArrayList<FavoriteDTO>();
        if (CollectionUtils.isNotEmpty(favoriteList)) {
            for (Favorite favorite : favoriteList) {
                favoriteDTOList.add(this.dozerMapper.map(favorite, FavoriteDTO.class, "favorite2favoriteDTO"));
            }
        }

        return favoriteDTOList;
    }

    @Override
    public List<FavoriteDTO> getByUserId(int userId) {
        List<FavoriteProd> favoriteList = this.favoriteDAO.selectByUserId(userId);
        List<FavoriteDTO> favoriteDTOList = new ArrayList<FavoriteDTO>();
        if (CollectionUtils.isNotEmpty(favoriteList)) {
            for (FavoriteProd favorite : favoriteList) {
                favoriteDTOList.add(this.dozerMapper.map(favorite, FavoriteDTO.class, "favoriteProd2favoriteDTO"));
            }
        }
        return favoriteDTOList;
    }

    @Override
    public void add(FavoriteDTO favoriteDTO) {
        Favorite favorite = this.dozerMapper.map(favoriteDTO, Favorite.class, "favoriteDTO2favorite");
        this.favoriteDAO.insertSelective(favorite);
    }
}
