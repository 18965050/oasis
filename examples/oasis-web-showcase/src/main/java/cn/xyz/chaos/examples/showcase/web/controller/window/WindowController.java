package cn.xyz.chaos.examples.showcase.web.controller.window;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author mfan
 * @version 1.0.0
 */
@Controller
@RequestMapping("/window/window")
public class WindowController {

    @RequestMapping(value = "windowintegration", method = RequestMethod.GET)
    public String windowIntegration() {
        return "window/windowintegration";
    }

    @RequestMapping(value = "windowextend", method = RequestMethod.GET)
    public String windowExtend() {
        return "window/windowextend";
    }

    @RequestMapping(value = "windowuse", method = RequestMethod.GET)
    public String windowUse() {
        return "window/windowuse";
    }
}
