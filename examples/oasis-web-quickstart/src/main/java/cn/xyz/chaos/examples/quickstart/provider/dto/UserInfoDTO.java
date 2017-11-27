package cn.xyz.chaos.examples.quickstart.provider.dto;

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

    private Long id;

    private String name;

    private String nickName;

    private String email;

    private String mobile;

    private Integer status;

    private String weiboUrl;

    private String doubanUrl;

    private String signature;

    private Date createTime;

    private Long createUserId;

    private Date updateTime;

    private Long updateUserId;

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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getWeiboUrl() {
        return weiboUrl;
    }

    public void setWeiboUrl(String weiboUrl) {
        this.weiboUrl = weiboUrl;
    }

    public String getDoubanUrl() {
        return doubanUrl;
    }

    public void setDoubanUrl(String doubanUrl) {
        this.doubanUrl = doubanUrl;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
}
