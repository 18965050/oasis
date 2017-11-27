package cn.xyz.chaos.validator.test;

import cn.xyz.chaos.validator.config.impl.EasyXmlValidatorResolver;

public class EasyXmlValidatorResolverTest {
    public static void main(String[] args) {
        new EasyXmlValidatorResolver().resolverEntity();
        new EasyXmlValidatorResolver().resolveValidator();
    }
}
