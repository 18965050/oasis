package cn.xyz.chaos.examples.demo.web.controller.auth;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xyz.chaos.examples.demo.provider.dto.UserInfoDTO;
import cn.xyz.chaos.examples.demo.web.dvo.UserInfoDVO;
import cn.xyz.chaos.examples.demo.web.service.AuthService;
import cn.xyz.chaos.validator.ValidEasy;

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

    @RequestMapping(value = "login" /* , method = RequestMethod.GET */)
    public String login() {
        return "auth/auth_login";
    }

    @RequestMapping(value = "qlogin", method = RequestMethod.POST)
    @ResponseBody
    public String qlogin(UserInfoDTO userInfoDTO) {
        return this.authService.qlogin(userInfoDTO);
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(UserInfoDVO userInfoDVO) {
        return "auth/auth_register";
    }

    @RequestMapping(value = "registerpost", method = RequestMethod.POST)
    public String registerPost(@ValidEasy("register") UserInfoDVO userInfoDVO, BindingResult bindingResult,
            Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("result", "fail");
            model.addAttribute("error", bindingResult.getAllErrors());
            return "auth/auth_register";
        }
        Map<String, Object> rsObjs = authService.register(userInfoDVO);
        if ("success".equals(rsObjs.get("result"))) {
            return "redirect:/auth/auth/login";
        }
        model.addAllAttributes(rsObjs);
        return "auth/auth_register";
    }

}
