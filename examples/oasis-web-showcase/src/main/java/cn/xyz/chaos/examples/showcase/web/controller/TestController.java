package cn.xyz.chaos.examples.showcase.web.controller;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.Sets;

/**
 * Created with IntelliJ IDEA. User: qgan(qgan@v5.cn) Date: 14-3-10 Time: 上午10:59 To change this template use File |
 * Settings | File Templates.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController implements ApplicationContextAware, InitializingBean {
    private static final Logger log = LoggerFactory.getLogger(TestController.class);

    private static final String CONTROLLER_PACKAGE = ".web.controller.";
    private static final Set<String> EXCLUDE_CONTROLLERS = Sets.newHashSet();
    private static final String LOGIN_METHOD_NAME = "bindDevice";

    @Override
    public void afterPropertiesSet() throws Exception {
        EXCLUDE_CONTROLLERS.add("cn.v5.web.controller.TestController");
    }

    @Autowired(required = false)
    ServletContext servletContext;

    private ApplicationContext ctx;

    boolean isHandler(Class<?> beanType) {
        return ((AnnotationUtils.findAnnotation(beanType, Controller.class) != null)
                || (AnnotationUtils.findAnnotation(beanType, RequestMapping.class) != null));
    }

    boolean exculdeController(String className) {
        return EXCLUDE_CONTROLLERS.contains(className);
    }

    @RequestMapping("test")
    public void test(Integer u, HttpServletResponse response, HttpServletRequest request) throws Exception {
        LocalVariableTableParameterNameDiscoverer nameDiscoverer = new LocalVariableTableParameterNameDiscoverer();
        String base = "http://localhost:8080/chaos";// ConfigUtils.getString("base.url");
        String root = servletContext.getRealPath("/");
        log.debug("web server base url is {}", base);
        log.debug("root path is {}", root);

        File testPage = new File(root + File.separator + "static" + File.separator + "apiTest.html");
        if ((u == null) && testPage.exists() && (testPage.length() > 0)) {
            request.getRequestDispatcher("/static/apiTest.html").forward(request, response);
            return;
            // response.sendRedirect("/static/apiTest.html");
        }

        testPage.createNewFile();
        List<String> lines = new ArrayList<String>();
        lines.add(
                "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"utf-8\"><script type=\"text/javascript\" src=\"/static/bootstrap/jquery-1.10.2.min.js\">"
                        + "</script><link rel=\"stylesheet\" type=\"text/css\" href=\"/static/bootstrap/css/bootstrap.min.css\">"
                        + "<link rel=\"stylesheet\" type=\"text/css\" href=\"/static/bootstrap/css/bootstrap-theme.min.css\">"
                        + "<script type=\"text/javascript\" src=\"/static/bootstrap/js/bootstrap.min.js\"></script>"
                        + "<script type=\"text/javascript\" src=\"/static/bootstrap/jquery.cookie.js\"></script>"
                        + "</head>" + "<body>"
                        + "<div class=\"container\"><nav class=\"navbar navbar-default\" role=\"navigation\">"
                        + "<div class=\"navbar-header\">"
                        + "<button type=\"button\" class=\"navbar-toggle\" data-toggle=\"collapse\" data-target=\"#bs-example-navbar-collapse-1\">"
                        + "<span class=\"sr-only\">Toggle navigation</span>" + "<span class=\"icon-bar\"></span>"
                        + "<span class=\"icon-bar\"></span>" + "<span class=\"icon-bar\"></span>" + "</button>"
                        + "<a class=\"navbar-brand\" href=\"#\">HttpServer Api Test</a>" + "</div></nav>"
                        + "<div class='alert alert-warning alert-dismissable'>" + "<strong>说明！</strong>" + "<ul>"
                        + "<li>不支持部分方法（上传文件，下载文件等）测试</li>" + "<li>部分方法先登录后测试（先调用/api/user/bind/device）</li>" + "</ul>"
                        + "</div>" + "</div>" + "<div id=\"wrap\">" + "<div class=\"container\"><div class=\"row\">");

        StringBuilder left = new StringBuilder("<div class=\"col-md-3\"><ul class=\"nav nav-pills nav-stacked\">");
        StringBuilder right = new StringBuilder("<div class=\"col-md-9\">");

        String[] beanNames = ctx.getBeanNamesForType(Object.class);
        int i = 0;
        for (String beanName : beanNames) {
            Class<?> clazz = ctx.getType(beanName);
            String className = clazz.getCanonicalName();
            if ((className.indexOf(CONTROLLER_PACKAGE) < 0) || !isHandler(clazz) || exculdeController(className)) {
                continue;
            }
            RequestMapping classAnnotation = AnnotationUtils.findAnnotation(clazz, RequestMapping.class);
            String urlPrefix = "";
            if ((classAnnotation != null) && (classAnnotation.value() != null)) {
                urlPrefix = classAnnotation.value()[0];
            }

            String actionClass = clazz.getSimpleName();
            if (StringUtils.indexOf(actionClass, "$$") > 0) {
                Object obj = ctx.getBean(beanName);
                log.debug("aop proxy is {}", AopUtils.isAopProxy(obj));
                clazz = AopUtils.getTargetClass(obj);
                actionClass = clazz.getSimpleName();
            }
            log.debug("action class name is {}", actionClass);
            Method[] methods = clazz.getMethods();

            for (Method method : methods) {
                RequestMapping methodAnnotation = AnnotationUtils.findAnnotation(method, RequestMapping.class);
                if (methodAnnotation == null) {
                    continue;
                }
                String[] values = methodAnnotation.value();
                String httpMethod = "*";
                if ((methodAnnotation.method() != null) && (methodAnnotation.method().length > 0)) {
                    httpMethod = methodAnnotation.method()[0].name();
                }

                String path = urlPrefix + values[0];
                log.debug("url is {}", path);
                left.append("<li " + (i == 0 ? "class=\"active\"" : "") + ">");
                left.append("<a href=\"#" + path + "\">" + httpMethod + "&nbsp;&nbsp;" + path + "</a>");
                left.append("</li>");

                // 右侧内容
                right.append("<div id='" + path + "' class=\"panel panel-default\">");
                right.append("<div class=\"panel-heading\"><h4>" + httpMethod + "&nbsp;&nbsp;" + path + "</h4></div>");
                right.append("<div class=\"panel-body\">");
                right.append("<div class=\"form-horizontal\" role=\"form\">");

                String[] paramsNames = nameDiscoverer.getParameterNames(method);
                log.debug("paramNames is {}", paramsNames);
                String mName = method.getName();

                if ((paramsNames != null) && (paramsNames.length > 0)) {
                    Class<?>[] paramTypes = method.getParameterTypes();
                    int index = 0;
                    for (String paramName : paramsNames) {
                        if (paramTypes[index].isAssignableFrom(HttpServletResponse.class)
                                || paramTypes[index].isAssignableFrom(HttpServletRequest.class)) {
                            index++;
                            continue;
                        }
                        index++;

                        right.append("<div class=\"form-group\">");
                        boolean flag = false;
                        right.append("<label for=\"" + mName + paramName + "\" class=\"col-sm-2 control-label\""
                                + (flag ? "style='color:red'" : "") + ">" + paramName + "</label>");
                        right.append("<div class=\"col-sm-10\">");
                        right.append("<input type=\"text\" class=\"form-control\" id=\"" + mName + paramName
                                + "\" placeholder=\"" + paramName + "\">");
                        right.append("</div></div>");
                    }
                }

                right.append("<div class=\"form-group\">");
                right.append("<label class=\"col-sm-2 control-label\">&nbsp;</label>");
                right.append("<div class=\"col-sm-10\">");
                right.append("<button type=\"submit\" class=\"btn btn-default\" id=\"" + actionClass + mName
                        + "\">发送请求</button>");
                right.append("</div></div>");

                right.append("</div></div>");

                right.append("<div class=\"alert alert-success\" style='display:none;' id='" + actionClass + mName
                        + "ok'></div>");
                right.append("<div class=\"alert alert-danger\" style='display:none;' id='" + actionClass + mName
                        + "fail'></div>");
                right.append("</div>");

                right.append("<script type=\"text/javascript\">");
                right.append("$('#" + actionClass + mName + "').click(function(){");
                right.append("$('#" + actionClass + mName + "ok').css('display', 'none');");
                right.append("$('#" + actionClass + mName + "fail').css('display', 'none');");
                right.append("$.ajax({");
                right.append("    type: \"" + httpMethod.toUpperCase() + "\",");
                if (path.indexOf("{") > 0) {
                    right.append("    url: '" + base + path.substring(0, path.indexOf("{")) + "'"); // rest方式
                    int j = 0;
                    if ((paramsNames != null) && (paramsNames.length > 0)) {
                        for (String pName : paramsNames) {
                            if (j > 0) {
                                right.append("/");
                            }
                            right.append("+$('#" + mName + pName + "').val()");
                            j++;
                        }
                    }
                } else {
                    right.append("    url: '" + base + path + "'");
                    if ((paramsNames != null) && (paramsNames.length > 0)) {
                        right.append(",data: {");
                        int j = 0;
                        for (String pName : paramsNames) {
                            if (j > 0) {
                                right.append(",");
                            }
                            right.append(pName + ":$('#" + mName + pName + "').val()");
                            j++;
                        }
                        right.append("}");
                    }
                }
                right.append(",headers:{'client-session':$.cookie('client-session')}");// header

                right.append("}).done(function(msg, status, xhr){"); // 请求成功
                right.append(
                        "var cnt = 'response header:<br/>' + xhr.getAllResponseHeaders()+'<br/>--------------<br/>response:' + xhr.responseText;");
                right.append("$('#" + actionClass + mName + "ok').html(cnt);");
                right.append("$('#" + actionClass + mName + "ok').css('display', 'block');");
                if (LOGIN_METHOD_NAME.equals(mName)) { // 登陆后保存session id
                    // right.append("$.cookie('client-session', xhr.getResponseHeader('client-session'));");
                    right.append("var resp = eval('('+xhr.responseText+')');");
                    right.append("$.cookie('client-session', resp.session_id);");
                }
                right.append("}).fail(function(xhr, textStatus) {"); // 请求失败
                right.append(
                        "var cnt = 'response status:'+xhr.status+'<br/>response header:<br/>' + xhr.getAllResponseHeaders()+'<br/>--------------<br/>response:' + xhr.responseText;");
                right.append("$('#" + actionClass + mName + "fail').html(cnt);");
                right.append("$('#" + actionClass + mName + "fail').css('display', 'block');");
                right.append("});");
                right.append("});");
                right.append("</script>");

                i++;
            }
        }

        left.append("</ul></div>");
        right.append("</div>");
        lines.add(left.toString());
        lines.add(right.toString());
        lines.add("</div></div></div></body>");
        FileUtils.writeLines(testPage, lines);

        // response.sendRedirect("/static/apiTest.html");
        request.getRequestDispatcher("/static/apiTest.html").forward(request, response);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }
}
