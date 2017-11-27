package cn.xyz.chaos.examples.quickstart.web.dvo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import cn.xyz.chaos.orm.mybatis.easylist.list.base.SearchType;
import cn.xyz.chaos.orm.mybatis.easylist.list.base.annotation.SearchItem;

/**
 * UserDVO <br/>
 * User 页面值对象
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年7月29日 下午2:15:52
 * @author lcg
 */
public class UserDVO {

    @SearchItem(value = "au.id", searchType = SearchType.NUMBER_EQUAL)
    private Long id;

    @SearchItem(value = "au.name", searchType = SearchType.TEXT_LIKE)
    private String name;

    @SearchItem(value = "au.nick_name", searchType = SearchType.TEXT_LIKE)
    private String nickName;

    @SearchItem(value = "au.email", searchType = SearchType.TEXT_LIKE)
    private String email;

    @SearchItem(value = "au.mobile", searchType = SearchType.TEXT_LIKE)
    private String mobile;

    @SearchItem(value = "au.status", searchType = SearchType.NUMBER_EQUAL)
    private Integer status;

    private String password;

    private String rptPassword;

    private String weiboUrl;

    private String doubanUrl;

    private String signature;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

    @SearchItem(value = "au.create_time", searchType = SearchType.DATE_BETWEEN)
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date[] betweenCreateTime = new Date[2];

    /**
     * 分页参数，第几页
     */
    private int pageIndex = 0;

    /**
     * 分页参数，每页多少条
     */
    private int limit = 10;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
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
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
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
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
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
     * @return the weiboUrl
     */
    public String getWeiboUrl() {
        return weiboUrl;
    }

    /**
     * @param weiboUrl the weiboUrl to set
     */
    public void setWeiboUrl(String weiboUrl) {
        this.weiboUrl = weiboUrl;
    }

    /**
     * @return the doubanUrl
     */
    public String getDoubanUrl() {
        return doubanUrl;
    }

    /**
     * @param doubanUrl the doubanUrl to set
     */
    public void setDoubanUrl(String doubanUrl) {
        this.doubanUrl = doubanUrl;
    }

    /**
     * @return the signature
     */
    public String getSignature() {
        return signature;
    }

    /**
     * @param signature the signature to set
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * @return the createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the createTime to set
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the createUser
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * @param createUser the createUser to set
     */
    public void setCreateUser(String createUser) {
        this.createUser = createUser;
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

    /**
     * @return the updateUser
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * @param updateUser the updateUser to set
     */
    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * @return the limit
     */
    public int getLimit() {
        return limit;
    }

    /**
     * @param limit the limit to set
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * @return the betweenCreateTime
     */
    public Date[] getBetweenCreateTime() {
        return betweenCreateTime;
    }

    /**
     * @param betweenCreateTime the betweenCreateTime to set
     */
    public void setBetweenCreateTime(Date[] betweenCreateTime) {
        this.betweenCreateTime = betweenCreateTime;
    }

    /**
     * @return the pageIndex
     */
    public int getPageIndex() {
        return pageIndex;
    }

    /**
     * @param pageIndex the pageIndex to set
     */
    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getRptPassword() {
        return rptPassword;
    }

    public void setRptPassword(String rptPassword) {
        this.rptPassword = rptPassword;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
