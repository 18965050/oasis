package cn.xyz.chaos.orm.sqlmap.impl;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;

import cn.xyz.chaos.orm.mybatis.MyBatisDAO;
import cn.xyz.chaos.orm.sqlmap.AbstractSqlmapAspect;
import cn.xyz.chaos.orm.sqlmap.sqlsession.MyBatisSqlSession;
import cn.xyz.chaos.orm.sqlmap.sqlsession.SqlSession;

/**
 * Created by lcg on 14/12/18 018.
 */
@Aspect
public class MyBatisSqlmapAspect extends AbstractSqlmapAspect {

    protected SqlSession createSqlSession(ProceedingJoinPoint joinPoint) {
        SqlSession sqlSession = null;
        if (joinPoint.getTarget() instanceof MyBatisDAO) {
            MyBatisDAO mybatisDAO = (MyBatisDAO) joinPoint.getTarget();
            sqlSession = sqlSessions.get(mybatisDAO.getSqlSession());
            if (sqlSession == null) {
                sqlSession = new MyBatisSqlSession(mybatisDAO.getSqlSession());
                sqlSessions.putIfAbsent(mybatisDAO.getSqlSession(), sqlSession);
            }
        }
        return sqlSession;
    }
}
