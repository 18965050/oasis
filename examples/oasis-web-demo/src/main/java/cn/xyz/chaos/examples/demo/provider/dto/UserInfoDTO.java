package cn.xyz.chaos.examples.demo.provider.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * UserInfoDTO <br/>
 * DTO
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年8月12日 下午5:36:59
 * @author lcg
 */
public class UserInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private String email;

    private String password;

    private Integer type;

    private Integer status;

    private Date addTime;

    private Date updateTime;

    /**
     * @return the userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
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
     * @return the addTime
     */
    public Date getAddTime() {
        return addTime;
    }

    /**
     * @param addTime the addTime to set
     */
    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

}
