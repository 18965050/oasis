package cn.xyz.chaos.examples.showcase.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/showcase")
public class ShowCaseController {

    private Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("baseutils")
    public String baseUtils() {
        return "showcase/baseutils";
    }

    @RequestMapping("logusage")
    public String logUsage() {
        log.debug("aaa");
        return "showcase/logusage";

    }

    @RequestMapping("logspec")
    public String logSpec() {
        return "showcase/logspec";
    }
}
