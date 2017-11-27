package cn.xyz.chaos.orm.ibatis.mysql;

import cn.xyz.chaos.orm.generator.GeneratorLauncher;

public class IbatisGcMysqlTask {

    public static void main(String... strings) throws Exception {
        GeneratorLauncher.generate(ClassLoader.getSystemResource("ibatis/mysql/gc/ibatis-gc.xml").getPath());
    }
}
