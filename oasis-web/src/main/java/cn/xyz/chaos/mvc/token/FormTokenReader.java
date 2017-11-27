package cn.xyz.chaos.mvc.token;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import cn.xyz.chaos.mvc.web.RequestContext;

/**
 * cn.xyz.chaos.mvc.token
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/2 15:15.
 */
public class FormTokenReader implements ClientTokenReader {

    public static final String DEFAULT_FORM_UNIQUE_ID = "_form_unique_id";

    /**
     * token字段在form表单中的name，默认为 {@value #DEFAULT_FORM_UNIQUE_ID}
     */
    private String tokenKeyInForm = DEFAULT_FORM_UNIQUE_ID;

    @Override
    public Token read(RequestContext requestContext) {
        HttpServletRequest request = requestContext.getRequest();
        String secret = request.getParameter(tokenKeyInForm);
        if (StringUtils.isEmpty(secret)) {
            return null;
        }
        return new Token(secret);
    }

    /**
     * @see #tokenKeyInForm
     * @return
     */
    public void setTokenKeyInForm(String tokenKeyInForm) {
        this.tokenKeyInForm = tokenKeyInForm;
    }
}
