package cn.xyz.chaos.orm.ibatis.h2;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import cn.xyz.chaos.orm.ibatis.h2.dao.SsTaskDAO;

import java.util.List;

/**
 * Created by lcg on 14/12/17 017.
 */
@DirtiesContext
@ActiveProfiles("h2")
@ContextConfiguration(locations = { "/applicationContext.xml", "/ibatis/h2/ibatis-spring.xml" })
public class IbatsDAOH2Test extends AbstractTransactionalJUnit4SpringContextTests {
    @Autowired
    private SsTaskDAO ssTaskDAO;

    @Test
    public void h2_paginator_test() {
        Assert.assertTrue(ssTaskDAO.selectByPaginator() == 2);
    }

    @Test
    public void h2_sqlmap_test() {
        List<SsTaskDTO> list = ssTaskDAO.getAll(2);
        Assert.assertTrue(list.size() == 3);
    }

}
