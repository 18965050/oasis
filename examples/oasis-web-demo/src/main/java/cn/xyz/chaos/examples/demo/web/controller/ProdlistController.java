package cn.xyz.chaos.examples.demo.web.controller;

import java.util.Map;

import cn.xyz.chaos.mvc.shiro.annotation.RequiresAuthc;
import org.apache.shiro.authz.annotation.RequiresGuest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.xyz.chaos.examples.demo.web.service.ProdlistService;
import cn.xyz.chaos.examples.demo.web.svo.ProdlistSVO;

/**
 * ProdlistController <br/>
 * 前台产品列表
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年8月13日 上午11:26:55
 * @author lcg
 */
@Controller
@RequestMapping("/prodlist")
@RequiresAuthc
public class ProdlistController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProdlistService prodlistService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    @RequiresAuthc(authenticated = false)
    public String list(ProdlistSVO prodlistSVO, Model model) {
        log.info("enter prodlist page");
        Map<String, Object> rsObjs = prodlistService.list(prodlistSVO);
        model.addAllAttributes(rsObjs);
        return "prodlist_list";
    }

    @RequestMapping(value = "ceshi1")
    @RequiresRoles("tests")
    @RequiresAuthc(authenticated = false)
    public String test1() {
        return "prodlist_test1";
    }

    @RequestMapping(value = "ceshi2")
    public String test2() {
        return "prodlist_test2";
    }

    @RequestMapping(value = "ceshi3")
    @RequiresGuest
    @RequiresAuthc(authenticated = false)
    public String test3() {
        return "prodlist_test3";
    }

}
