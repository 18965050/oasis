package cn.xyz.chaos.jakarta.common.application;

import static org.hamcrest.CoreMatchers.is;

import java.net.URL;
import java.util.List;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationFactory;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.XMLConfiguration;
import org.junit.Assert;
import org.junit.Test;

/**
 * {@link org.apache.commons.configuration.Configuration}单元测试
 * <p />
 * Configuration提供了对属性文件,XML等多种文件格式进行解析的工具类
 *
 * @author lvchenggang
 *
 */
public class ConfigurationTest {

    /**
     * {@link org.apache.commons.configuration.PropertiesConfiguration}测试
     *
     * @throws Exception
     */
    @Test
    public void testProperties() throws Exception {

        Configuration config = new PropertiesConfiguration("./jakarta/test.properties");
        Assert.assertThat(config.getFloat("speed"), is(Float.valueOf("23.332")));
        Assert.assertThat((String) config.getList("names").get(0), is("Bob"));
        Assert.assertThat((boolean) config.getBoolean("correct"), is(false));
    }

    /**
     * {@link org.apache.commons.configuration.XMLConfiguration}测试
     *
     * @throws Exception
     */
    @Test
    public void testXML() throws Exception {
        Configuration config = new XMLConfiguration("./jakarta/global.xml");
        List startCriteria = config.getList("start-criteria.criteria");
        int horsepower = config.getInt("horsepower");
        Assert.assertThat(startCriteria.size(), is(2));
        Assert.assertThat(horsepower, is(42));

    }

    /**
     * 混合测试
     *
     * @throws Exception
     */
    @Test
    public void testComposite() throws Exception {
        ConfigurationFactory factory = new ConfigurationFactory();
        URL configURL = getClass().getResource("/jakarta/configuration.xml");
        factory.setConfigurationURL(configURL);

        Configuration config = factory.getConfiguration();
        Assert.assertThat(config.getFloat("timeout"), is(Float.valueOf("15.52")));
        Assert.assertThat(config.getString("region"), is("Virginia"));
        Assert.assertThat(config.getString("name"), is("Jarrod Hood"));
        Assert.assertThat(config.getInt("speed"), is(5000));
    }
}
