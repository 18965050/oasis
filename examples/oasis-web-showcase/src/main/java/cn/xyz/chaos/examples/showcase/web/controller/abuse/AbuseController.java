package cn.xyz.chaos.examples.showcase.web.controller.abuse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.xyz.chaos.mvc.abuse.AbuseLocker;
import cn.xyz.chaos.mvc.web.RequestContext;

/**
 * cn.xyz.chaos.examples.showcase.web.controller.abuse
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/5 14:14.
 */
@Controller
@RequestMapping("/abuse/abuse")
public class AbuseController {

    @Qualifier("abuseLocker")
    @Autowired
    private AbuseLocker abuseLocker;

    @RequestMapping(value = "unlockview", method = RequestMethod.GET)
    public String unlockview(HttpServletRequest request, HttpServletResponse response) {
        return "abuse/abuse_unlockView";
    }

    @RequestMapping(value = "unlock", method = RequestMethod.POST)
    public String unlock(HttpServletRequest request, HttpServletResponse response) {
        RequestContext requestContext = RequestContext.getRequestContext(request, response);
        abuseLocker.unlock(requestContext, 300);
        return "redirect:/";
    }

}
