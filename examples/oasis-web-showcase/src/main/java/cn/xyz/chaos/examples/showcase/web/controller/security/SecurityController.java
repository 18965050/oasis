package cn.xyz.chaos.examples.showcase.web.controller.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * cn.xyz.chaos.examples.showcase.web.controller.security
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2014/9/18 9:52.
 */
@Controller
@RequestMapping("/security/security")
public class SecurityController {

    @RequestMapping(value = "shirointegration", method = RequestMethod.GET)
    public String shiroIntegration() {
        return "security/securityshirointegration";
    }

    @RequestMapping(value = "shirouse", method = RequestMethod.GET)
    public String shiroUse() {
        return "security/securityuse";
    }

}
