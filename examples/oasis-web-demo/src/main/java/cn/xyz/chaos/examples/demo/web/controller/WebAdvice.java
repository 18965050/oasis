package cn.xyz.chaos.examples.demo.web.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import cn.xyz.chaos.validator.spring.EasyValidator;

// @ControllerAdvice
public class WebAdvice {
    private final EasyValidator easyValidator = new EasyValidator();

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
        if ((binder.getTarget() != null) && easyValidator.supports(binder.getTarget().getClass())) {
            binder.addValidators(easyValidator);
        }
    }

}
