package cn.xyz.chaos.examples.demo.web.controller.membercenter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xyz.chaos.examples.demo.provider.CompanyProvider;
import cn.xyz.chaos.examples.demo.provider.dto.CompanyDTO;
import cn.xyz.chaos.examples.demo.provider.dto.ProductDTO;
import cn.xyz.chaos.examples.demo.web.service.ProductService;
import cn.xyz.chaos.validator.ValidEasy;

@Controller
@RequestMapping(value = "/membercenter/product")
@RequiresRoles("admin")
public class ProductController {

    @Autowired
    private CompanyProvider companyProvider;

    @Autowired
    private ProductService productService;

    @ModelAttribute("company")
    public Map<Integer, String> createCompany() {
        List<CompanyDTO> companyList = this.companyProvider.list();
        Map<Integer, String> map = new HashMap<Integer, String>();
        if (!CollectionUtils.isEmpty(companyList)) {
            for (CompanyDTO company : companyList) {
                map.put(company.getComId(), company.getComName());
            }
        }
        return map;
    }

    @ModelAttribute("productStatus")
    public Map<Integer, String> createProductStatus() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(0, "N/A");
        map.put(1, "有效");
        map.put(2, "无效");
        return map;
    }

    @RequestMapping(value = "list")
    public String list(ProductDTO queryDTO, ModelMap model) {
        Map<String, Object> map = this.productService.list(queryDTO);
        model.addAllAttributes(map);
        model.addAttribute("queryDTO", queryDTO);
        // return "tile_product_list";
        return "membercenter/product/list";
    }

    @RequestMapping("input")
    public String input(ProductDTO productDTO, ModelMap model) {
        if (productDTO.getProdId() == null) {
            // return "tile_product_add";
            return "membercenter/product/input_add";
        } else {
            ProductDTO product = this.productService.get(productDTO.getProdId());
            // 将picLoc设置为空
            product.setPicLoc("");
            model.addAttribute("product", product);
            // return "tile_product_update";
            return "membercenter/product/input_update";
        }
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ValidEasy("default") @ModelAttribute("product") ProductDTO productDTO,
            BindingResult bindingResult) {
        String fileName = productDTO.getPicLoc();
        if (!StringUtils.isBlank(fileName)) {
            String appPath = System.getProperty("web.root");
            productDTO.setPicLoc(appPath + "/WEB-INF/upload/" + fileName);
        }
        if (productDTO.getProdId() == null) {
            if (bindingResult.hasErrors()) {
                // return "tile_product_add";
                return "membercenter/product/input_add";
            }
            this.productService.add(productDTO);
            if (productDTO.hasError()) {
                // return "tile_product_add";
                return "membercenter/product/input_add";
            }
        } else {
            if (bindingResult.hasErrors()) {
                // return "tile_product_update";
                return "membercenter/product/input_update";
            }
            this.productService.update(productDTO);
            if (productDTO.hasError()) {
                // return "tile_product_update";
                return "membercenter/product/input_update";
            }
        }

        return "redirect:/membercenter/product/list";
    }

    @RequestMapping("detail/{id}")
    public String detail(@PathVariable int id, ModelMap model) {
        this.productService.detail(id, model);
        // return "tile_product_detail";
        return "membercenter/product/detail";
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        this.productService.delete(id);
        return "redirect:/membercenter/product/list";
    }

    @RequestMapping("bind/{id}")
    public String bind(@PathVariable int id, ModelMap model) {
        this.productService.bind(id, model);
        // return "tile_product_bind";
        return "membercenter/product/bind";
    }

    @RequestMapping("bindattr")
    @ResponseBody
    public String bindAttr(@RequestParam("prodId") int prodId, @RequestParam("prodAttr") String prodAttr) {
        this.productService.bindAttr(prodId, prodAttr);
        return "/membercenter/product/list";
    }
}
