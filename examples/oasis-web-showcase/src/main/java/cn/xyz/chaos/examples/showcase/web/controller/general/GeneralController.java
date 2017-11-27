package cn.xyz.chaos.examples.showcase.web.controller.general;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * cn.xyz.chaos.examples.showcase.web.controller.general
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2014/9/24 16:34.
 */
@Controller
@RequestMapping("/general/general")
public class GeneralController {

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String user() {
        return "general/generaluser";
    }

    @RequestMapping(value = "role", method = RequestMethod.GET)
    public String role() {
        return "general/generalrole";
    }

    @RequestMapping(value = "pms", method = RequestMethod.GET)
    public String pms() {
        return "general/generalpms";
    }

    @RequestMapping(value = "demo", method = RequestMethod.GET)
    public String demo() {
        return "general/generaldemo";
    }

}
