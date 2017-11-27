package cn.xyz.chaos.examples.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.xyz.chaos.examples.demo.web.service.UserInfoService;

/**
 * UserController <br/>
 * 用户管理
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年7月28日 下午2:24:39
 * @author lcg
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserInfoService userService;

}
