package cn.xyz.chaos.examples.showcase.web.controller.validator;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.xyz.chaos.examples.showcase.web.dvo.UserDTO;
import cn.xyz.chaos.validator.ValidEasy;

/**
 * @author mfan
 * @version 1.0.0
 */
@Controller
@RequestMapping("/validator/validator")
public class ValidatorController {

    @RequestMapping(value = "validatorintegration", method = RequestMethod.GET)
    public String validatorIntegration() {
        return "validator/validatorintegration";
    }

    @RequestMapping(value = "validatorextend", method = RequestMethod.GET)
    public String validatorExtend() {
        return "validator/validatorextend";
    }

    @RequestMapping(value = "validatoruse", method = RequestMethod.GET)
    public String validatorUse() {
        return "validator/validatoruse";
    }

    @RequestMapping(value = "validatorsubmit", method = RequestMethod.POST)
    public String validatorSubmit(@ValidEasy UserDTO userDTO, BindingResult result) {
        return "validator/validatoruse";
    }
}
