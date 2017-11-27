#set($symbol_pound='#')#set($symbol_dollar='$')#set($symbol_escape='\')package ${package}.web.dvo;

public class UserDTO {

    private String logUsername;
    private String logPassword;
    private String logPassword2;
    private String mobile;
    private String phone;
    private String atleastOne;

    public String getLogUsername() {
        return logUsername;
    }

    public void setLogUsername(String logUsername) {
        this.logUsername = logUsername;
    }

    public String getLogPassword() {
        return logPassword;
    }

    public void setLogPassword(String logPassword) {
        this.logPassword = logPassword;
    }

    public String getLogPassword2() {
        return logPassword2;
    }

    public void setLogPassword2(String logPassword2) {
        this.logPassword2 = logPassword2;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAtleastOne() {
        return atleastOne;
    }

}
