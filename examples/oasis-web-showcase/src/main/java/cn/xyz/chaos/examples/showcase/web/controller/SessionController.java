package cn.xyz.chaos.examples.showcase.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * cn.xyz.chaos.examples.showcase.web.controller
 *
 * @author lcg
 * @version 1.0.0 Created by lcg on 2015/1/13 15:48.
 */
@Controller
@RequestMapping("/session")
public class SessionController {

    @Value(value = "#{systemProperties['app.num']}")
    private String app;

    public static final String varName = "_s_p_t_var";

    @RequestMapping("init")
    @ResponseBody
    public Object init(HttpSession session) {
        int count = 0;
        session.setAttribute(varName, count);
        return count;
    }

    @RequestMapping("increase")
    @ResponseBody
    public Object increase(HttpSession session) {
        int count = (Integer) session.getAttribute(varName);
        count += 1;
        session.setAttribute(varName, count);
        return count;
    }

    @RequestMapping("increase4")
    @ResponseBody
    public Object increase4(HttpServletRequest request, HttpServletResponse response, HttpSession session, Model model)
            throws ServletException, IOException {
        String id = session.getId();
        model.addAttribute("sid", id);

        urlGet("http://localhost/session/increase;JSESSIONID=" + id);
        urlGet("http://localhost/session/increase;JSESSIONID=" + id);
        urlGet("http://localhost/session/increase;JSESSIONID=" + id);
        urlGet("http://localhost/session/increase;JSESSIONID=" + id);

        int count = (Integer) session.getAttribute(varName);
        return count;
    }

    @RequestMapping("check")
    @ResponseBody
    public Object check(HttpSession session) {
        int count = (Integer) session.getAttribute(varName);
        return count;
    }

    @RequestMapping("test1")
    public String test1(@RequestParam(required = false, value = "var") String var, Model model) {

        if (StringUtils.isBlank(var)) {
            var = "test";
        }
        model.addAttribute("var", var);
        model.addAttribute("appNum", app);
        return "session_test1";
    }

    @RequestMapping("test2")
    public String test2(Model model) {
        model.addAttribute("appNum", app);
        return "session_test2";
    }

    private void urlGet(String string) {
        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) new URL(string).openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("content-type", "text/html");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            try {
                inputStream.read();
            } finally {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
