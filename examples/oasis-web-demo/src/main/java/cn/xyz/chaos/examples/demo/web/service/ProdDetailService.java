package cn.xyz.chaos.examples.demo.web.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import cn.xyz.chaos.examples.demo.provider.CommentProvider;
import cn.xyz.chaos.examples.demo.provider.FavoriteProvider;
import cn.xyz.chaos.examples.demo.provider.ProductProvider;
import cn.xyz.chaos.examples.demo.provider.dto.CommentDTO;
import cn.xyz.chaos.examples.demo.provider.dto.FavoriteDTO;

@Service
public class ProdDetailService {

    @Autowired
    private CommentProvider commentProvider;

    @Autowired
    private ProductProvider productProvider;

    @Autowired
    private FavoriteProvider favoriteProvider;

    public void detail(Integer prodId, ModelMap model) {
        model.addAttribute("product", productProvider.get(prodId));
        model.addAttribute("commentList", this.commentProvider.getByProdId(prodId));
    }

    public String addFavorite(FavoriteDTO favoriteDTO) {
        List<FavoriteDTO> favoriteDTOList = this.favoriteProvider.selectBySelective(favoriteDTO);
        if (CollectionUtils.isNotEmpty(favoriteDTOList)) {
            return "{\"retCode\":1,\"msg\":\"用户收藏失败,请确认用户是否已收藏此产品\"}";
        }
        favoriteDTO.setAddTime(new Date());
        this.favoriteProvider.add(favoriteDTO);
        return "{\"retCode\":0,\"msg\":\"用户收藏成功\"}";
    }

    public String addComment(CommentDTO commentDTO) {
        List<CommentDTO> commentDTOList = this.commentProvider.selectBySelective(commentDTO);
        if (CollectionUtils.isNotEmpty(commentDTOList)) {
            return "fail";
        }
        commentDTO.setAddTime(new Date());
        this.commentProvider.add(commentDTO);
        return "success";
    }
}
