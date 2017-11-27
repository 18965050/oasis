package cn.xyz.chaos.orm.ibatis.mysql.dao;

import cn.xyz.chaos.common.page.PageReq;
import cn.xyz.chaos.common.page.PageRes;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.stereotype.Repository;

import cn.xyz.chaos.orm.annotations.Sqlmap;
import cn.xyz.chaos.orm.ibatis.IbatisDAOPlus;
import cn.xyz.chaos.orm.ibatis.mysql.SsTaskDTO;
import cn.xyz.chaos.orm.ibatis.mysql.entity.SsTask;
import cn.xyz.chaos.orm.ibatis.mysql.entity.SsTaskCriteria;

@Repository
public class SsTaskDAO extends IbatisDAOPlus<SsTask, SsTaskCriteria, SsTaskDTO> {
    public int selectByPaginator() {
        SsTaskCriteria c = new SsTaskCriteria();
        PageRes<SsTask> p2 = super.selectByCriteriaWithPageReq(c, new PageReq(1, 2));
        return p2.getPageCount();
    }

    @Sqlmap(Sqlmap.Dml.SELECT)
    public int query() {
        return 0;
    }

    @Override
    protected void addConverter(ConverterRegistry registry) {
    }
}
