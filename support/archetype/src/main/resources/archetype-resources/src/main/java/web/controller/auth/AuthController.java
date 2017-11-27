#set($symbol_pound='#')#set($symbol_dollar='$')#set($symbol_escape='\')package ${package}.web.controller.auth;

import ${package}.web.dvo.UserDVO;import ${package}.web.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * AuthController <br/>
 * 认证Controller
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年6月25日 下午8:03:07
 * @author lcg
 */
@RequestMapping("/auth/auth")
@Controller
public class AuthController {

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "login"/* , method = RequestMethod.GET */)
    public String login() {
        return "auth/authlogin";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register() {
        return "auth/authregister";
    }

    @RequestMapping(value = "registerpost", method = RequestMethod.POST)
    public String registerPost(UserDVO userDVO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("result", "fail");
            model.addAttribute("error", bindingResult.getAllErrors());
            return "auth/authregister";
        }
        Map<String, Object> rsObjs = authService.register(userDVO);
        if ("success".equals(rsObjs.get("result"))) {
            return "redirect:/auth/auth/login";
        }
        model.addAllAttributes(rsObjs);
        return "auth/authregister";
    }

}
