package cn.xyz.chaos.examples.showcase.web.controller.token;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * cn.xyz.chaos.examples.showcase.web.controller.token
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/2 18:46.
 */
@RequestMapping("/token/token")
@Controller
public class TokenController {

    @RequestMapping(value = "formview", method = RequestMethod.GET)
    public String formView() {
        return "token/token_formView";
    }

    @RequestMapping(value = "form", method = RequestMethod.POST)
    public String form() {
        return "token/token_formView";
    }

}
