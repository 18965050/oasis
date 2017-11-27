package cn.xyz.chaos.examples.demo.web.service;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.xyz.chaos.common.utils.SpringContextUtils;
import cn.xyz.chaos.examples.demo.authc.AuthUtils;
import cn.xyz.chaos.examples.demo.authc.entity.AccInfo;
import cn.xyz.chaos.examples.demo.authc.entity.KeyUsernamePwdToken;
import cn.xyz.chaos.examples.demo.provider.UserInfoProvider;
import cn.xyz.chaos.examples.demo.provider.consts.UserInfoConst;
import cn.xyz.chaos.examples.demo.provider.dto.UserInfoDTO;
import cn.xyz.chaos.examples.demo.web.dvo.UserInfoDVO;

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
    private UserInfoProvider userInfoProvider;

    public AuthenticationInfo getAuthInfo(KeyUsernamePwdToken token, String realmName) throws AuthenticationException {
        // 实际情况可以根据邮箱、用户名、手机号等
        // 甚至验证也在这一步做了，总之，获得一个匹配的账号
        UserInfoDTO userInfo = userInfoProvider.getByEmail(token.getUsername());
        if (null == userInfo) {
            throw new UnknownAccountException("账号不存在");
        }
        if (userInfo.getStatus() != UserInfoConst.STATUS_VALID) {
            throw new DisabledAccountException("账号被冻结/账号类型错误……");
        }
        AccInfo accInfo = new AccInfo(userInfo);
        return new SimpleAuthenticationInfo(accInfo, userInfo.getPassword(), realmName);
    }

    public String qlogin(UserInfoDTO userInfoDTO) {
        org.apache.shiro.mgt.SecurityManager securityManager = SpringContextUtils.getBean("securityManager");
        Subject subject = SecurityUtils.getSubject();
        KeyUsernamePwdToken token = new KeyUsernamePwdToken(userInfoDTO.getEmail(), userInfoDTO.getPassword(), false,
                null);
        try {
            subject = securityManager.login(subject, token);
            AccInfo accInfo = (AccInfo) subject.getPrincipal();
            return "{\"retCode\":0,\"userId\":" + accInfo.getId() + "}";
        } catch (AuthenticationException ex) {
            return "{\"retCode\":1}";
        }
    }

    /**
     * 用户注册
     *
     * @param userInfoDVO
     * @return
     */
    public Map<String, Object> register(UserInfoDVO userInfoDVO) {
        Map<String, Object> rsObjs = Maps.newHashMap();

        boolean exist = userInfoProvider.isEmailExist(userInfoDVO.getEmail());
        boolean rs = !exist;
        if (rs) {
            UserInfoDTO userinfo = new UserInfoDTO();
            userinfo.setEmail(userInfoDVO.getEmail());
            userinfo.setPassword(userInfoDVO.getPassword());
            userinfo.setType(userInfoDVO.getType());
            UserInfoDTO added = userInfoProvider.add(userinfo);
            rs = null != added;
        } else {
            rsObjs.put("msg", "email 已经注册！");
        }
        rsObjs.put("result", rs ? "success" : "fail");
        return rsObjs;
    }

}
