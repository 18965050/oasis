package cn.xyz.chaos.orm.mybatis.mysql;

import cn.xyz.chaos.orm.mybatis.mysql.dao.SsTaskDAO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springside.modules.test.category.UnStable;

/**
 * Created by lcg on 14/12/17 017.
 */
@DirtiesContext
@ActiveProfiles("mybatis-mysql")
// @RunWith(SpringJUnit4ClassRunner.class)
// @Transactional
@ContextConfiguration(locations = { "/applicationContext.xml", "/mybatis/mysql/mybatis-spring.xml" })
@Category(UnStable.class)
public class MybatsDAOMySqlTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private SsTaskDAO ssTaskDAO;

    @Test
    public void paginator_test() {
        Assert.assertTrue(ssTaskDAO.selectByPaginator() == 2);
    }
}
