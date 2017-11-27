package cn.xyz.chaos.examples.demo.web.controller.membercenter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/membercenter/membercenter")
public class MemberCenterController {

    @RequestMapping("index")
    public String index() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.hasRole("normal")) {
            return "redirect:/membercenter/comment/list";
        } else {
            return "redirect:/membercenter/company/list";
        }
    }

}
