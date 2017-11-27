package cn.xyz.chaos.examples.showcase.web.dvo;

import cn.xyz.chaos.orm.mybatis.easylist.list.base.SearchType;
import cn.xyz.chaos.orm.mybatis.easylist.list.base.annotation.SearchItem;
import cn.xyz.chaos.orm.mybatis.easylist.list.base.annotation.SortItem;

/**
 * AccountDVO <br/>
 * DVO
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年7月9日 下午5:43:26
 * @author lcg
 */
public class AccountDVO {

    private Long id;

    private String password;

    @SearchItem(value = "name", searchType = SearchType.TEXT_LIKE)
    @SortItem()
    private String name;

    @SearchItem(value = "nickName", searchType = SearchType.TEXT_LIKE)
    private String nickName;

    @SearchItem(value = "email", searchType = SearchType.TEXT_EQUAL, index = -10)
    private String email;

    @SearchItem(value = "mobile", searchType = SearchType.TEXT_EQUAL)
    private String mobile;

    @SearchItem(value = "status", searchType = SearchType.NUMBER_EQUAL)
    private Integer status;

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
}
