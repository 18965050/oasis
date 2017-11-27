package cn.xyz.chaos.examples.showcase.web.controller.window;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.xyz.chaos.mvc.window.Window;
import cn.xyz.chaos.mvc.window.WindowAsync;

@Controller
@RequestMapping("/window/windowasync")
public class WindowAsyncController {
    @RequestMapping(value = "index")
    public String index(WindowAsync windowAsync) {
        Window w1 = new Window("w1", "/window/windowasync/w1");
        Window w2 = new Window("w2", "/window/windowasync/w2");
        windowAsync.invoke(w1, w2);
        return "window/windowasync_index";
    }

    @RequestMapping(value = "w1")
    public String w1(WindowAsync windowAsync) {
        return "window/windowasync_w1";
    }

    @RequestMapping(value = "w2")
    public String w2(HttpServletRequest request, HttpServletResponse response) {
        return "window/windowasync_w2";
    }

}
