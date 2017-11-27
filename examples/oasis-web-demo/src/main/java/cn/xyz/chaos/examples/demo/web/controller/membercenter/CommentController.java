package cn.xyz.chaos.examples.demo.web.controller.membercenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.xyz.chaos.examples.demo.provider.ProductProvider;
import cn.xyz.chaos.examples.demo.provider.UserInfoProvider;
import cn.xyz.chaos.examples.demo.provider.dto.CommentDTO;
import cn.xyz.chaos.examples.demo.provider.dto.ProductDTO;
import cn.xyz.chaos.examples.demo.provider.dto.UserInfoDTO;
import cn.xyz.chaos.examples.demo.web.service.CommentService;

@Controller
@RequestMapping(value = "/membercenter/comment")
@RequiresRoles(value = { "normal", "admin" }, logical = Logical.OR)
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private ProductProvider productProvider;

    @Autowired
    private UserInfoProvider userProvider;

    @ModelAttribute("product")
    public Map<Integer, String> createProduct() {
        List<ProductDTO> productList = this.productProvider.list();
        Map<Integer, String> map = new HashMap<Integer, String>();
        if (!CollectionUtils.isEmpty(productList)) {
            for (ProductDTO product : productList) {
                map.put(product.getProdId(), product.getProdName());
            }
        }
        return map;
    }

    @ModelAttribute("user")
    public Map<Integer, String> createUser() {
        List<UserInfoDTO> userList = this.userProvider.list();
        Map<Integer, String> map = new HashMap<Integer, String>();
        if (!CollectionUtils.isEmpty(userList)) {
            for (UserInfoDTO user : userList) {
                map.put(user.getUserId(), user.getEmail());
            }
        }
        return map;
    }

    @RequestMapping(value = "list")
    public String list(CommentDTO queryDTO, ModelMap model) {
        Map<String, Object> map = this.commentService.list(queryDTO);
        model.addAllAttributes(map);
        model.addAttribute("queryDTO", queryDTO);
        // return "tile_comment_list";
        return "membercenter/comment/list";
    }

}
