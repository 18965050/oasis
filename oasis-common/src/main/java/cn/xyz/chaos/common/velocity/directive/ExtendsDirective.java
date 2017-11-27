package cn.xyz.chaos.common.velocity.directive;

import org.apache.velocity.runtime.directive.Parse;

/**
 * @author badqiu
 */
public class ExtendsDirective extends Parse {
    @Override
    public String getName() {
        return "extends";
    }

}
