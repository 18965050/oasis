package cn.xyz.chaos.examples.showcase.web.controller.mybatis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * cn.xyz.chaos.examples.showcase.web.controller.mybatis
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2014/9/22 13:35.
 */
@Controller
@RequestMapping("/mybatis/mybatis")
public class MybatisController {

    @RequestMapping(value = "mybatisintegration", method = RequestMethod.GET)
    public String mybatisIntegration() {
        return "mybatis/mybatisintegration";
    }

    @RequestMapping(value = "mybatisgenerator", method = RequestMethod.GET)
    public String mybatisGenerator() {
        return "mybatis/mybatisgenerator";
    }

    @RequestMapping(value = "mybatisuse", method = RequestMethod.GET)
    public String mybatisUse() {
        return "mybatis/mybatisuse";
    }
}
