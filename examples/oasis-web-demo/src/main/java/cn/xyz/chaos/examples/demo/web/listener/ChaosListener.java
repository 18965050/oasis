package cn.xyz.chaos.examples.demo.web.listener;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.web.context.ContextLoaderListener;

public class ChaosListener extends ContextLoaderListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext context = event.getServletContext();

        String appPath = context.getRealPath("/");
        if (appPath.endsWith(File.separator)) {
            appPath = appPath.substring(0, appPath.length() - 1);
        }

        System.setProperty("web.root", appPath);
        super.contextInitialized(event);
    }

}
