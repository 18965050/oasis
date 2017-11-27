package cn.xyz.chaos.orm.sqlmap.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

import cn.xyz.chaos.orm.ibatis.IbatisDAO;
import cn.xyz.chaos.orm.sqlmap.AbstractSqlmapAspect;
import cn.xyz.chaos.orm.sqlmap.sqlsession.IbatisSqlSession;
import cn.xyz.chaos.orm.sqlmap.sqlsession.SqlSession;

/**
 * Created by lcg on 14/12/18 018.
 */
@Aspect
public class IbatisSqlmapAspect extends AbstractSqlmapAspect {

    @SuppressWarnings("deprecation")
    protected SqlSession createSqlSession(ProceedingJoinPoint joinPoint) {
        SqlSession sqlSession = null;
        if (joinPoint.getTarget() instanceof IbatisDAO) {
            IbatisDAO ibatisDAO = (IbatisDAO) joinPoint.getTarget();
            sqlSession = sqlSessions.get(ibatisDAO.getSqlMapClientTemplate());
            if (sqlSession == null) {
                sqlSession = new IbatisSqlSession(ibatisDAO.getSqlMapClientTemplate());
                sqlSessions.putIfAbsent(ibatisDAO.getSqlMapClientTemplate(), sqlSession);
            }
        }
        return sqlSession;
    }
}
