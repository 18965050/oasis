package cn.xyz.chaos.orm.ibatis.mysql;

import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import cn.xyz.chaos.orm.ibatis.mysql.dao.SsTaskDAO;
import org.springside.modules.test.category.UnStable;

import java.util.List;

/**
 * Created by lcg on 14/12/17 017.
 */
@DirtiesContext
@ActiveProfiles("mysql")
// @RunWith(SpringJUnit4ClassRunner.class)
// @Transactional
@ContextConfiguration(locations = { "/applicationContext.xml", "/ibatis/mysql/ibatis-spring.xml" })
@Category(UnStable.class)
public class IbatsDAOMySqlTest extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private SsTaskDAO ssTaskDAO;

    @Test
    public void paginator_test() {
        Assert.assertTrue(ssTaskDAO.selectByPaginator() == 2);
    }

    @Test
    public void sqlmap_test() {
        int count = ssTaskDAO.query();
        Assert.assertTrue(count != 0);
    }
}
