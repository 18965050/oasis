package cn.xyz.chaos.examples.demo.web.dvo;

import cn.xyz.chaos.examples.demo.provider.dto.UserInfoDTO;

/**
 * UserInfoDVO <br/>
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年8月21日 上午10:58:33
 * @author lcg
 */
public class UserInfoDVO extends UserInfoDTO {

    private static final long serialVersionUID = 1L;

    private String email;

    private String rptPassword;

    /**
     * @return the rptPassword
     */
    public String getRptPassword() {
        return rptPassword;
    }

    /**
     * @param rptPassword the rptPassword to set
     */
    public void setRptPassword(String rptPassword) {
        this.rptPassword = rptPassword;
    }

    /**
     * @return the email
     */
    @Override
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    @Override
    public void setEmail(String email) {
        this.email = email;
    }

}
