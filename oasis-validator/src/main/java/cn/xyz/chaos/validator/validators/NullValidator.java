package cn.xyz.chaos.validator.validators;

import cn.xyz.chaos.validator.ValidContext;

public class NullValidator extends AbstractValidator {

    @Override
    public boolean isValid(Object object, ValidContext validContext) {
        return object == null;
    }

}
