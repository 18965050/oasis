package cn.xyz.chaos.examples.showcase.web.controller.easylist;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * cn.xyz.chaos.examples.showcase.web.controller.easylist
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2014/9/23 17:28.
 */
@Controller
@RequestMapping("/easylist/easylist")
public class EasyListController {

    @RequestMapping(value = "integration", method = RequestMethod.GET)
    public String integration() {
        return "easylist/easylistintegration";
    }

    @RequestMapping(value = "use", method = RequestMethod.GET)
    public String use() {
        return "easylist/easylistuse";
    }

}
