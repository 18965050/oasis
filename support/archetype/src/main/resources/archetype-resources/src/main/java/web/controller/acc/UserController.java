#set($symbol_pound='#')#set($symbol_dollar='$')#set($symbol_escape='\')package ${package}.web.controller.acc;

import java.util.Map;

import ${package}.web.service.acc.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ${package}.web.dvo.UserDVO;

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
        return "acc/userlist";
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
