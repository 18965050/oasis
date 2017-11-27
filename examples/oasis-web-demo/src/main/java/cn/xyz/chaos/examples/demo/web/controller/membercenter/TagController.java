package cn.xyz.chaos.examples.demo.web.controller.membercenter;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xyz.chaos.examples.demo.provider.dto.TagDTO;
import cn.xyz.chaos.examples.demo.web.service.TagService;

@Controller
@RequestMapping(value = "/membercenter/tag")
@RequiresRoles("admin")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "list")
    public String list(TagDTO queryDTO, ModelMap model) {
        Map<String, Object> map = this.tagService.list(queryDTO);
        model.addAllAttributes(map);
        model.addAttribute("queryDTO", queryDTO);
        // return "tile_tag_list";
        return "membercenter/tag/list";
    }

    @RequestMapping(value = "update")
    @ResponseBody
    public String update(TagDTO tagDTO) {
        try {
            if (tagDTO.getSkuId() == null) {
                this.tagService.add(tagDTO);
            } else {
                this.tagService.update(tagDTO);
            }
            return "success";
        } catch (Exception e) {
            return "fail";
        }
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        this.tagService.delete(id);
        return "redirect:/membercenter/tag/list";
    }

    @RequestMapping("input")
    @ResponseBody
    public String input(TagDTO tagDTO, ModelMap model) {
        tagDTO = this.tagService.detail(tagDTO.getSkuId());
        return "{\"id\":\"" + tagDTO.getSkuId() + "\",\"name\":\"" + tagDTO.getSkuName() + "\"}";
    }

}
