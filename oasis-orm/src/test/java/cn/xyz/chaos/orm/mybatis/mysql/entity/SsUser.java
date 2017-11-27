package cn.xyz.chaos.orm.mybatis.mysql.entity;

import cn.xyz.chaos.orm.annotations.Table;
import java.util.Date;

/** lcg@2014-12-18 16:11:02 */
@Table(name = "cn.xyz.chaos.orm.mybatis.mysql.entity.SsUser", schema = "test")
public class SsUser {
    private Long id;

    private String loginName;

    private String name;

    private String password;

    private String salt;

    private String roles;

    private Date registerDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }
}
