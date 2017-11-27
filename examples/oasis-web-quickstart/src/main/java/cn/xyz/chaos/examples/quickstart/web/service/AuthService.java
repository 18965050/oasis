package cn.xyz.chaos.examples.quickstart.web.service;

import java.util.Map;

import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xyz.chaos.examples.quickstart.authc.entity.AccInfo;
import cn.xyz.chaos.examples.quickstart.authc.entity.KeyUsernamePwdToken;
import cn.xyz.chaos.examples.quickstart.entity.Account;
import cn.xyz.chaos.examples.quickstart.provider.AccountProvider;
import cn.xyz.chaos.examples.quickstart.web.dvo.UserDVO;
import cn.xyz.chaos.examples.quickstart.web.service.acc.UserService;

import com.google.common.collect.Maps;

/**
 * AuthService <br/>
 * 认证前台服务
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年6月25日 下午8:08:36
 * @author lcg
 */
@Service
public class AuthService {

    @Autowired
    private AccountProvider accountProvider;

    @Autowired
    private UserService userService;

    public AuthenticationInfo getAuthInfo(KeyUsernamePwdToken token, String realmName) throws AuthenticationException {
        // 实际情况可以根据邮箱、用户名、手机号等
        // 甚至验证也在这一步做了，总之，获得一个匹配的账号
        Account account = accountProvider.getByEmail(token.getUsername());
        if (null == account) {
            throw new UnknownAccountException("账号不存在");
        }
        if (account.getStatus() != 1) {// 只是个示例
            throw new DisabledAccountException("账号被冻结/账号类型错误……");
        }
        AccInfo userInfo = new AccInfo(account);
        return new SimpleAuthenticationInfo(userInfo, account.getPassword(), realmName);
    }

    /**
     * 用户注册
     *
     * @param userDVO
     * @return
     */
    public Map<String, Object> register(UserDVO userDVO) {
        Map<String, Object> rsObjs = Maps.newHashMap();

        boolean exist = accountProvider.isEmailExist(userDVO.getEmail());
        boolean rs = !exist;
        if (rs) {
            Map<String, Object> add = userService.add(userDVO);
            rs = add.containsKey("result") && add.get("result").equals("success");
        } else {
            rsObjs.put("msg", "email 已经注册！");
        }
        rsObjs.put("result", rs ? "success" : "fail");
        return rsObjs;
    }
}
