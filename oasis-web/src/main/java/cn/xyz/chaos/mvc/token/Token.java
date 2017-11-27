package cn.xyz.chaos.mvc.token;

import java.io.Serializable;
import java.util.Date;

/**
 * cn.xyz.chaos.mvc.token
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/1/30 15:16.
 */
public class Token implements Comparable<Token>, Serializable {

    private static final long serialVersionUID = 1L;

    private final String ip;

    private final String secret;

    private final Date createTime;

    public Token(String secret) {
        this(secret, null, new Date());
    }

    public Token(String secret, String ip) {
        this.secret = secret;
        this.ip = ip;
        this.createTime = new Date();
    }

    public Token(String secret, String ip, Date createTime) {
        this.ip = ip;
        this.secret = secret;
        this.createTime = createTime;
    }

    @Override
    public int compareTo(Token that) {
        int rs = 1;
        if (null != that) {
            rs = this.createTime.compareTo(that.getCreateTime());
            if (0 == rs && null != this.secret && null != that.secret) {
                rs = this.secret.compareTo(that.secret);
            }
        }
        return rs;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getSecret() {
        return secret;
    }

    public String getIp() {
        return ip;
    }

}
