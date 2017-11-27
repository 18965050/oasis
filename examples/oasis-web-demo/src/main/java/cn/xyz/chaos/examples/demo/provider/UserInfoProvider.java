package cn.xyz.chaos.examples.demo.provider;

import java.util.List;

import cn.xyz.chaos.examples.demo.provider.dto.UserInfoDTO;

/**
 * AccountProvider <br/>
 * Account服务接口
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年6月24日 下午5:28:43
 * @author lcg
 */
public interface UserInfoProvider {
    /**
     * 新增一个用户
     *
     * @param account
     * @return
     */
    UserInfoDTO add(UserInfoDTO userinfo);

    /**
     * 获得email代表的账户
     *
     * @param email email
     * @return 邮箱对应的账户，否则为null
     */
    UserInfoDTO getByEmail(String email);

    /**
     * 获得ID代表的账户
     *
     * @param id id
     * @return ID对应的账户，否则为null
     */
    UserInfoDTO getById(int id);

    /**
     * 是否存在email对应的账户
     *
     * @param email email
     * @return true/false
     */
    boolean isEmailExist(String email);

    List<UserInfoDTO> list();

}
