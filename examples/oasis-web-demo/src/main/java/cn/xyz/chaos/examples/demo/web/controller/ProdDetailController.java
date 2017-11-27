package cn.xyz.chaos.examples.demo.web.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xyz.chaos.examples.demo.provider.dto.CommentDTO;
import cn.xyz.chaos.examples.demo.provider.dto.FavoriteDTO;
import cn.xyz.chaos.examples.demo.web.service.ProdDetailService;

@Controller
@RequestMapping("/proddetail")
public class ProdDetailController {
    @Autowired
    private ProdDetailService prodDetailService;

    @RequestMapping(value = "detail/{prodId}")
    public String detail(@PathVariable int prodId, ModelMap model) {
        this.prodDetailService.detail(prodId, model);
        // return "tile_prod_template";
        return "proddetail/detail";
    }

    @RequestMapping("addFavorite")
    @ResponseBody
    public String add(FavoriteDTO favoriteDTO) {
        return this.prodDetailService.addFavorite(favoriteDTO);
    }

    @RequestMapping(value = "commentinput")
    @ResponseBody
    public String commentInput() {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            return "login";
        } else {
            return "comment";
        }
    }

    @RequestMapping(value = "addcomment")
    @ResponseBody
    public String addComment(CommentDTO commentDTO) {
        return this.prodDetailService.addComment(commentDTO);
    }

}
