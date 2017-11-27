package cn.xyz.chaos.mvc.token;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * cn.xyz.chaos.mvc.token
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/2 17:42.
 */
public class TokenGetterWrapper {

    private HttpServletRequest request;
    private HttpServletResponse response;

    private TokenManager tokenManager;

    public TokenGetterWrapper(HttpServletRequest request, HttpServletResponse response, TokenManager tokenManager) {
        this.request = request;
        this.response = response;
        this.tokenManager = tokenManager;
    }

    public String getNewToken() {
        return tokenManager.newToken(request, response).getSecret();
    }

    @Override
    public String toString() {
        return getNewToken().toString();
    }
}
