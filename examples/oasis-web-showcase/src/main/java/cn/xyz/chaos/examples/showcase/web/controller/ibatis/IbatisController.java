package cn.xyz.chaos.examples.showcase.web.controller.ibatis;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 */
@Controller
@RequestMapping("/ibatis/ibatis")
public class IbatisController {

    @RequestMapping(value = "ibatisintegration", method = RequestMethod.GET)
    public String ibatisIntegration() {
        return "ibatis/ibatisintegration";
    }

    @RequestMapping(value = "ibatisgenerator", method = RequestMethod.GET)
    public String ibatisGenerator() {
        return "ibatis/ibatisgenerator";
    }

    @RequestMapping(value = "ibatisuse", method = RequestMethod.GET)
    public String ibatisUse() {
        return "ibatis/ibatisuse";
    }
}
