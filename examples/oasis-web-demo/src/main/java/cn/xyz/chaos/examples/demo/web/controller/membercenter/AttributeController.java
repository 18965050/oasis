package cn.xyz.chaos.examples.demo.web.controller.membercenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xyz.chaos.examples.demo.provider.TagProvider;
import cn.xyz.chaos.examples.demo.provider.dto.AttributeDTO;
import cn.xyz.chaos.examples.demo.provider.dto.TagDTO;
import cn.xyz.chaos.examples.demo.web.service.AttributeService;

@Controller
@RequestMapping(value = "/membercenter/attribute")
@RequiresRoles("admin")
public class AttributeController {

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private TagProvider tagProvider;

    @ModelAttribute("tag")
    public Map<Integer, String> createTag() {
        List<TagDTO> tagDTOList = this.tagProvider.list();
        Map<Integer, String> map = new HashMap<Integer, String>();
        if (!CollectionUtils.isEmpty(tagDTOList)) {
            for (TagDTO tag : tagDTOList) {
                map.put(tag.getSkuId(), tag.getSkuName());
            }
        }
        return map;
    }

    @RequestMapping(value = "list")
    public String list(AttributeDTO queryDTO, ModelMap model) {
        Map<String, Object> map = this.attributeService.list(queryDTO);
        model.addAllAttributes(map);
        model.addAttribute("queryDTO", queryDTO);
        // return "tile_attribute_list";
        return "membercenter/attribute/list";
    }

    @RequestMapping("input")
    public String input(AttributeDTO attrDTO, ModelMap model) {
        if (attrDTO.getSkuOptionId() == null) {
            // return "tile_attribute_add";
            return "membercenter/attribute/input_add";
        } else {
            attrDTO = this.attributeService.detail(attrDTO.getSkuOptionId());
            model.addAttribute("attr", attrDTO);
            // return "tile_attribute_update";
            return "membercenter/attribute/input_update";
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(AttributeDTO attrDTO) {
        if (attrDTO.getSkuOptionId() == null) {
            this.attributeService.add(attrDTO);
        } else {
            this.attributeService.update(attrDTO);
        }
        return "redirect:/membercenter/attribute/list";
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        this.attributeService.delete(id);
        return "redirect:/membercenter/attribute/list";
    }

    @RequestMapping("detail/{id}")
    public String detail(@PathVariable int id, ModelMap model) {
        model.addAttribute("attr", this.attributeService.detail(id));
        // return "tile_attribute_detail";
        return "membercenter/attribute/detail";
    }

    @RequestMapping("gettagattrs")
    @ResponseBody
    public String getTagAttrs(@RequestParam("tagId") int tagId) {
        return this.attributeService.getTagAttrs(tagId);
    }

}
