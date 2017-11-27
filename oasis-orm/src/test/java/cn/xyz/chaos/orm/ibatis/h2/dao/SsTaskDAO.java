package cn.xyz.chaos.orm.ibatis.h2.dao;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterRegistry;
import org.springframework.stereotype.Repository;

import cn.xyz.chaos.common.page.PageReq;
import cn.xyz.chaos.common.page.PageRes;
import cn.xyz.chaos.orm.annotations.Sqlmap;
import cn.xyz.chaos.orm.ibatis.IbatisDAOPlus;
import cn.xyz.chaos.orm.ibatis.h2.SsTaskDTO;
import cn.xyz.chaos.orm.ibatis.h2.entity.SsTask;
import cn.xyz.chaos.orm.ibatis.h2.entity.SsTaskCriteria;

@Repository
public class SsTaskDAO extends IbatisDAOPlus<SsTask, SsTaskCriteria, SsTaskDTO> {
    public int selectByPaginator() {
        SsTaskCriteria c = new SsTaskCriteria();
        c.or().andIdBetween(1L, 10L);
        PageRes<SsTask> p2 = super.selectByCriteriaWithPageReq(c, new PageReq(1, 2));
        return p2.getPageCount();
    }

    @Sqlmap(Sqlmap.Dml.SELECT)
    public List<SsTaskDTO> getAll(int id) {
        return null;
    }

    @Override
    protected void addConverter(ConverterRegistry registry) {
        registry.addConverter(new Converter<SsTask, SsTaskDTO>() {
            @Override
            public SsTaskDTO convert(SsTask source) {
                SsTaskDTO dto = new SsTaskDTO();
                BeanUtils.copyProperties(source, dto);
                return dto;
            }
        });
    }
}
