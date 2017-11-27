package cn.xyz.chaos.examples.showcase.web.controller.validator;

import cn.xyz.chaos.validator.ValidContext;
import cn.xyz.chaos.validator.validators.AbstractValidator;

/**
 * 自定义扩展校验器
 *
 * @author mfan
 */
public class XxxValidator extends AbstractValidator {

    @Override
    public boolean isValid(Object object, ValidContext validContext) {
        // TODO具体校验规则
        return false;
    }

}
