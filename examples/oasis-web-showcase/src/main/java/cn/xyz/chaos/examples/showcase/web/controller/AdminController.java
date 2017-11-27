package cn.xyz.chaos.examples.showcase.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * AdminController <br/>
 * 后台管理首页
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年7月28日 下午2:35:44
 * @author lcg
 */
@RequestMapping("/admin")
@Controller
public class AdminController {

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        return "admin_index";
    }

}
