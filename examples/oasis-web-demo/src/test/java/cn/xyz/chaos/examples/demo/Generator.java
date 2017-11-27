package cn.xyz.chaos.examples.demo;

import cn.xyz.chaos.orm.generator.GeneratorLauncher;

/**
 * Generator <br/>
 *
 * @version 1.0.0 <br/>
 *          创建时间：2014年8月25日 上午9:53:18
 * @author lcg
 */
public class Generator {
    public static void main(String[] args) throws Exception {
        GeneratorLauncher.generate("E:/DevGit/chaos/chaos-web-demo/src/main/resources/mybatis/generatorConfig.xml");
    }
}
