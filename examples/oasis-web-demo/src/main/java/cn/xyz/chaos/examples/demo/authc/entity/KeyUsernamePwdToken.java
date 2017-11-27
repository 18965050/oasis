package cn.xyz.chaos.examples.demo.authc.entity;

import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * KeyUsernamePwdToken <br/>
 * KEY 用户名 密码 Token
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年6月25日 下午8:39:03
 * @author lcg
 */
public class KeyUsernamePwdToken extends UsernamePasswordToken {

    private static final long serialVersionUID = -6441149906355307256L;

    private String key;

    private boolean needNoMatch;

    public KeyUsernamePwdToken() {
        super();
    }

    /**
     * @param username
     * @param password
     * @param rememberMe
     * @param host
     * @param key
     */
    public KeyUsernamePwdToken(String username, String password, boolean rememberMe, String host, String key) {
        super(username, password, rememberMe, host);
        this.key = key;
    }

    /**
     * @param username
     * @param password
     * @param rememberMe
     * @param host
     * @param key
     */
    public KeyUsernamePwdToken(String username, char[] password, boolean rememberMe, String host, String key) {
        super(username, password, rememberMe, host);
        this.key = key;
    }

    /**
     * @param username
     * @param password
     * @param rememberMe
     * @param key
     */
    public KeyUsernamePwdToken(String username, String password, boolean rememberMe, String key) {
        super(username, password, rememberMe);
        this.key = key;
    }

    /**
     * @param username
     * @param password
     * @param rememberMe
     * @param key
     */
    public KeyUsernamePwdToken(String username, char[] password, boolean rememberMe, String key) {
        super(username, password, rememberMe);
        this.key = key;
    }

    /**
     * @param username
     * @param password
     * @param host
     * @param key
     */
    public KeyUsernamePwdToken(String username, String password, String host, String key) {
        super(username, password, host);
        this.key = key;
    }

    /**
     * @param username
     * @param password
     * @param host
     * @param key
     */
    public KeyUsernamePwdToken(String username, char[] password, String host, String key) {
        super(username, password, host);
        this.key = key;
    }

    /**
     * @param username
     * @param password
     * @param key
     */
    public KeyUsernamePwdToken(String username, String password, String key) {
        super(username, password);
        this.key = key;
    }

    /**
     * @param username
     * @param password
     * @param key
     */
    public KeyUsernamePwdToken(String username, char[] password, String key) {
        super(username, password);
        this.key = key;
    }

    /**
     * @param username
     * @param password
     * @param rememberMe
     */
    public KeyUsernamePwdToken(String username, String password, boolean rememberMe) {
        super(username, password, rememberMe);
    }

    /**
     * @param username
     * @param password
     */
    public KeyUsernamePwdToken(String username, String password) {
        super(username, password);
    }

    /**
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * @param key the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    public boolean isNeedNoMatch() {
        return needNoMatch;
    }

    public void setNeedNoMatch(boolean needNoMatch) {
        this.needNoMatch = needNoMatch;
    }

}
