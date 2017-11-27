package cn.xyz.chaos.examples.showcase.demo;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;
import org.springside.modules.test.jetty.JettyFactory;
import org.springside.modules.test.spring.Profiles;

/**
 * 使用Jetty运行调试Web应用, 在Console快速重载应用.
 *
 * @author calvin
 */
public class ShowcaseServerForNotEclipse {

    public static final int PORT = 8080;
    public static final String CONTEXT = "/";
    public static final String RELATIVE_RESOURCE_BASE = "src/main/webapp";
    public static final String[] TLD_JAR_NAMES = new String[] { "sitemesh", "spring-webmvc", "shiro-web", "tiles" };
    public static final String DEFAULT_DESCRIPTOR = "jetty/webdefault-windows.xml";

    public static void main(String[] args) throws Exception {
        // 设定Spring的profile
        Profiles.setProfileAsSystemProperty(Profiles.DEVELOPMENT);
        System.setProperty("app.num", "" + PORT);

        // 启动Jetty
        Server server = JettyFactory.createServerInSource(PORT, CONTEXT);
        // 重设webContext,包括路径，因为默认的路径只有eclipse可识别
        WebAppContext webAppContext = new WebAppContext();
        webAppContext.setContextPath(CONTEXT);
        webAppContext.setResourceBase(getAbsolutePath() + RELATIVE_RESOURCE_BASE);
        webAppContext.setDefaultsDescriptor(DEFAULT_DESCRIPTOR);
        server.setHandler(webAppContext);
        JettyFactory.setTldJarNames(server, TLD_JAR_NAMES);

        try {
            System.out.println("[HINT] Don't forget to set -XX:MaxPermSize=128m");

            server.start();
            System.out.println("Server running at http://localhost:" + PORT + CONTEXT);
            System.out.println("[HINT] Hit Enter to reload the application quickly");

            // 等待用户输入回车重载应用
            while (true) {
                char c = (char) System.in.read();
                if (c == '\n') {
                    JettyFactory.reloadContext(server);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

    public static String getAbsolutePath() {
        String path = null;
        String folderPath = ShowcaseServerForNotEclipse.class.getProtectionDomain().getCodeSource().getLocation()
                .getPath().substring(1);
        if (folderPath.indexOf("target") > 0) {
            path = folderPath.substring(0, folderPath.indexOf("target"));
        }
        return path;
    }
}
