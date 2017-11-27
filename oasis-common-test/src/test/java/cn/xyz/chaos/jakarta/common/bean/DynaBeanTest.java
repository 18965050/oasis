package cn.xyz.chaos.jakarta.common.bean;

import static org.hamcrest.CoreMatchers.is;

import org.apache.commons.beanutils.BasicDynaBean;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaProperty;
import org.apache.commons.beanutils.PropertyUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * {@link org.apache.commons.beanutils.DynaBean}单元测试
 * <p />
 * DynaBean提供了一种动态Bean生成方式
 *
 * @author lvchenggang
 *
 */
public class DynaBeanTest {

    /**
     * Bean动态生成测试
     *
     * @throws Exception
     */
    @Test
    public void testDynaBean() throws Exception {
        DynaProperty[] beanProperties = new DynaProperty[] { new DynaProperty("name", String.class),
                new DynaProperty("favoriteColor", String.class) };
        BasicDynaClass authorClass = new BasicDynaClass("Author", BasicDynaBean.class, beanProperties);
        DynaBean author = authorClass.newInstance();
        author.set("name", "lcg");
        author.set("favoriteColor", "blue");

        Assert.assertThat((String) PropertyUtils.getProperty(author, "name"), is("lcg"));
        Assert.assertThat((String) PropertyUtils.getProperty(author, "favoriteColor"), is("blue"));
    }
}
