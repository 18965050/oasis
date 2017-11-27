package cn.xyz.chaos.examples.demo.web.controller.membercenter;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xyz.chaos.examples.demo.provider.dto.FavoriteDTO;
import cn.xyz.chaos.examples.demo.web.service.FavoriteService;

@Controller
@RequestMapping("/membercenter/favorite")
public class FavoriteController {

    @Autowired
    private FavoriteService favoriteService;

    @RequestMapping("get/{userId}")
    @ResponseBody
    public String get(@PathVariable int userId) {
        List<FavoriteDTO> favoriteDTOList = this.favoriteService.get(userId);
        StringBuilder sb = new StringBuilder("[");
        if (CollectionUtils.isNotEmpty(favoriteDTOList)) {
            for (int i = 0; i < favoriteDTOList.size(); i++) {
                FavoriteDTO favoriteDTO = favoriteDTOList.get(i);
                sb.append("{\"userId\":" + favoriteDTO.getUserId() + ",\"prodId\":" + favoriteDTO.getProdId()
                        + ",\"prodName\":\"" + favoriteDTO.getProdName() + "\"}");
                if (i != favoriteDTOList.size() - 1) {
                    sb.append(",");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
