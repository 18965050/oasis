package cn.xyz.chaos.mvc.web;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.collections.CollectionUtils;

/**
 * cn.xyz.chaos.mvc.web 正则URI匹配器
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/2/2 16:17.
 */
public class RegexUrlMatcher extends AbstractUrlMatcher {

    private List<Pattern> matchList;

    public void setMatchList(List<String> regexStrs) {
        if (CollectionUtils.isNotEmpty(regexStrs)) {
            matchList = new ArrayList<Pattern>(regexStrs.size());
        }
        for (String s : regexStrs) {
            Pattern pattern = Pattern.compile(s);
            matchList.add(pattern);
        }
    }

    @Override
    protected boolean doMatch(String uri) {
        if (CollectionUtils.isNotEmpty(matchList)) {
            for (Pattern pattern : matchList) {
                if (pattern.matcher(uri).matches()) {
                    return true;
                }
            }
        }
        return false;
    }

}
