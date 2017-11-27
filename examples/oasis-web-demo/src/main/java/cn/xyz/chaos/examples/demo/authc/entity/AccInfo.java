package cn.xyz.chaos.examples.demo.authc.entity;

import java.io.Serializable;

import cn.xyz.chaos.examples.demo.provider.dto.UserInfoDTO;

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

    private Integer id;

    /**
     * 显示名称
     */
    private String name;

    private String email;

    private Integer status;

    private Integer type;

    public AccInfo(UserInfoDTO userInfo) {
        this.id = userInfo.getUserId();
        this.email = userInfo.getEmail();
        this.status = userInfo.getStatus();
        this.name = this.email;
        this.type = userInfo.getType();
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * @return the type
     */
    public Integer getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(Integer type) {
        this.type = type;
    }

}
