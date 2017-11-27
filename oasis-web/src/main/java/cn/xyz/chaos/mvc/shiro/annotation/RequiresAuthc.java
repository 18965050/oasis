package cn.xyz.chaos.mvc.shiro.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * cn.xyz.chaos.mvc.shiro.annotation
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2014/9/1 18:20.
 * @see org.apache.shiro.authz.annotation.RequiresAuthentication
 */
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RequiresAuthc {
    boolean authenticated() default true;
}
