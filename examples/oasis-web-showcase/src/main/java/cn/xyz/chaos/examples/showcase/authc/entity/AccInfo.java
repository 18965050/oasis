package cn.xyz.chaos.examples.showcase.authc.entity;

import java.io.Serializable;

import cn.xyz.chaos.examples.showcase.entity.Account;
import cn.xyz.chaos.examples.showcase.provider.dto.UserInfoDTO;
import org.apache.commons.lang3.StringUtils;

/**
 * UserInfo <br/>
 * 用户信息
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年6月25日 下午8:30:57
 * @author lcg
 */
public class AccInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    /**
     * 显示名称
     */
    private String name;

    private String email;

    private Integer status;

    public AccInfo(Account account) {
        this.id = account.getId();
        this.email = account.getEmail();
        this.status = account.getStatus();
        // 有昵称则显示昵称，无昵称用用户名，无用户名用email
        if (StringUtils.isNotBlank(account.getNickName())) {
            this.name = account.getNickName();
        } else if (StringUtils.isNotBlank(account.getName())) {
            this.name = account.getName();
        } else {
            this.name = this.email;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
