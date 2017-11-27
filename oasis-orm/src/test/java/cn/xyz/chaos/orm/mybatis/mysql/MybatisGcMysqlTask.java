package cn.xyz.chaos.orm.mybatis.mysql;

import cn.xyz.chaos.orm.generator.GeneratorLauncher;

public class MybatisGcMysqlTask {
    public static void main(String[] args) throws Exception {
        GeneratorLauncher.generate(ClassLoader.getSystemResource("mybatis/mysql/gc/mybatis-gc.xml").getPath());
    }
}
