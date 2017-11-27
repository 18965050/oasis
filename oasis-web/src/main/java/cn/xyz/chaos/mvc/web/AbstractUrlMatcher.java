package cn.xyz.chaos.mvc.web;

/**
 * cn.xyz.chaos.mvc.web
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/2 15:59.
 */
public abstract class AbstractUrlMatcher implements RequestMatcher {

    @Override
    public boolean match(RequestContext requestContext) {
        String uri = requestContext.getURI();
        return doMatch(uri);
    }

    protected abstract boolean doMatch(String uri);

}
