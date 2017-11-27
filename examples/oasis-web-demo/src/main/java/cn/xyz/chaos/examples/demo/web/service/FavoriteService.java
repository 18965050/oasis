package cn.xyz.chaos.examples.demo.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xyz.chaos.examples.demo.provider.FavoriteProvider;
import cn.xyz.chaos.examples.demo.provider.dto.FavoriteDTO;

@Service
public class FavoriteService {

    @Autowired
    private FavoriteProvider favoriteProvider;

    public List<FavoriteDTO> get(Integer userId) {
        return this.favoriteProvider.getByUserId(userId);
    }

}
