package cn.xyz.chaos.examples.demo.web.controller.membercenter;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.xyz.chaos.examples.demo.provider.dto.CompanyDTO;
import cn.xyz.chaos.examples.demo.web.service.CompanyService;
import cn.xyz.chaos.validator.ValidEasy;

@Controller
@RequestMapping(value = "/membercenter/company")
@RequiresRoles("admin")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @ModelAttribute("companyStatus")
    public Map<Integer, String> createCompanyStatus() {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(0, "N/A");
        map.put(1, "有效");
        map.put(2, "无效");
        return map;
    }

    @RequestMapping(value = "list")
    public String list(CompanyDTO queryDTO, ModelMap model) {
        Map<String, Object> map = this.companyService.list(queryDTO);
        model.addAllAttributes(map);
        model.addAttribute("queryDTO", queryDTO);
        return "membercenter/company/list";
        // return "tile_company_list";
    }

    @RequestMapping("input")
    public String input(CompanyDTO companyDTO, ModelMap model) {
        if (companyDTO.getComId() == null) {
            // return "tile_company_add";
            return "membercenter/company/input_add";
        } else {
            model.addAttribute("company", this.companyService.detail(companyDTO.getComId()));
            // return "tile_company_update";
            return "membercenter/company/input_update";
        }

    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(@ValidEasy({ "default" }) CompanyDTO companyDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "redirect:/membercenter/company/list";
        }

        if (companyDTO.getComId() == null) {
            this.companyService.add(companyDTO);
        } else {
            this.companyService.update(companyDTO);
        }
        return "redirect:/membercenter/company/list";
    }

    @RequestMapping("detail/{id}")
    public String detail(@PathVariable int id, ModelMap model) {
        model.addAttribute("company", this.companyService.detail(id));
        // return "tile_company_detail";
        return "membercenter/company/detail";
    }

    @RequestMapping("delete/{id}")
    public String delete(@PathVariable int id) {
        this.companyService.delete(id);
        return "redirect:/membercenter/company/list";
    }

}
