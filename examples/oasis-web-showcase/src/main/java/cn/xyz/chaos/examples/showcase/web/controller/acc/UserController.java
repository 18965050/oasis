package cn.xyz.chaos.examples.showcase.web.controller.acc;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.xyz.chaos.examples.showcase.web.dvo.UserDVO;
import cn.xyz.chaos.examples.showcase.web.service.acc.UserService;

/**
 * UserController <br/>
 * 用户管理
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年7月28日 下午2:24:39
 * @author lcg
 */
@Controller
@RequestMapping("/acc/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 列表页面
     *
     * @return
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list() {
        return "acc/user_list";
    }

    /**
     * @return
     */
    @RequestMapping(value = "listjson", method = RequestMethod.GET)
    @ResponseBody
    public Object listJson(UserDVO userDVO) {
        Map<String, Object> rsObjs = userService.list(userDVO);
        return rsObjs;
    }

    @RequestMapping(value = "addjson", method = RequestMethod.POST)
    @ResponseBody
    public Object addJson(UserDVO userDVO) {
        Map<String, Object> rsObjs = userService.add(userDVO);
        return rsObjs;
    }

}
