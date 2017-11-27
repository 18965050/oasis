package cn.xyz.chaos.orm.mybatis.mysql.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import cn.xyz.chaos.orm.mybatis.MyBatisDAO;
import cn.xyz.chaos.orm.mybatis.MyBatisRepository;
import cn.xyz.chaos.orm.mybatis.mysql.entity.SsTask;
import cn.xyz.chaos.orm.mybatis.mysql.entity.SsTaskCriteria;

@MyBatisRepository
public class SsTaskDAO extends MyBatisDAO<SsTask, SsTaskCriteria> {
    public int selectByPaginator() {
        SsTaskCriteria c = new SsTaskCriteria();
        RowBounds rowBounds = new RowBounds(1, 2);
        List<SsTask> list = super.selectByCriteriaWithRowbounds(c, rowBounds);
        return list.size();
    }
}
