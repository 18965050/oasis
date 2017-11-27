package cn.xyz.chaos.orm.generator;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mybatis 生成器的启动器
 *
 * @author lcg
 */
public class GeneratorLauncher {

    private static final Logger LOGGER = LoggerFactory.getLogger(GeneratorLauncher.class);

    private GeneratorLauncher() {
    }

    public static void generate(String configFilePath)
            throws IOException, XMLParserException, InvalidConfigurationException, SQLException, InterruptedException {
        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        File configFile = new File(configFilePath);
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for (String warning : warnings) {
            LOGGER.warn("warning:{}", warning);
        }
    }

}
