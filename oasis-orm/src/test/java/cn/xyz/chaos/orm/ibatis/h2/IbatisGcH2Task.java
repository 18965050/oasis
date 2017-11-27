package cn.xyz.chaos.orm.ibatis.h2;

import cn.xyz.chaos.orm.generator.GeneratorLauncher;

public class IbatisGcH2Task {

    public static void main(String... strings) throws Exception {
        GeneratorLauncher.generate(ClassLoader.getSystemResource("ibatis/h2/gc/ibatis-gc.xml").getPath());
    }
}
